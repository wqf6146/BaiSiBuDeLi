package com.lmm.wddog.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.lmm.wddog.util.fresco.MyBitmapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * Created by Administrator on 2017/1/28.
 */

public class GrabImageView extends View {
    private static final String TAG = SubsamplingScaleImageView.class.getSimpleName();

    private int mImageWidth,mImageHeight,mViewWidth;
    private BitmapRegionDecoder mDecoder;

    private Rect mSrcRect = new Rect(),mDstRect = new Rect();
    private volatile Rect mDrawRect = new Rect();
    private int mSampleSize;

    private Bitmap mDrawBitmap=null;

    private int SepHeight = 900;

    public GrabImageView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
    }

    public void drawGrabBitmap(final Bitmap bitmap){

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                int height =  bitmap.getWidth()*getHeight()/getWidth();

                mDrawBitmap = bitmap;
                mSrcRect = new Rect(0,0,bitmap.getWidth(),height);
                mDstRect = new Rect(0,0,getWidth(),getHeight());
                invalidate();
            }
        });


    }

    public void showGrabImage(InputStream inputStream, int imgwidth, int imgheight){

        try {
            //InputStream inputStream = resource.openStream();
            mDecoder = BitmapRegionDecoder.newInstance(inputStream,false);
           // mDecoder = BitmapRegionDecoder.newInstance(is, false);
//            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
//            tmpOptions.inJustDecodeBounds = true;
//            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
//            mImageWidth = tmpOptions.outWidth;
//            mImageHeight = tmpOptions.outHeight;

            mImageWidth = imgwidth;
            mImageHeight = imgheight;

            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    mDrawRect.top=0;
                    mDrawRect.bottom = SepHeight > mImageHeight ? mImageHeight : SepHeight;
                    mDrawRect.left=0;
                    mDrawRect.right = mViewWidth > mImageWidth ? mImageWidth : mViewWidth;

                    BitmapLoadTask loadTask = new BitmapLoadTask(GrabImageView.this, mDecoder,mDrawRect,
                            MyBitmapUtils.calculateInSampleSize(mImageWidth,mImageHeight,
                                    mDrawRect.right,mDrawRect.bottom));
                    // loadTask.execute();
                    execute(loadTask);
                }
            });


            //requestLayout();
            //invalidate();
            inputStream.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void execute(AsyncTask task){
        try {
            Field executorField = AsyncTask.class.getField("THREAD_POOL_EXECUTOR");
            Executor executor = (Executor)executorField.get(null);
            Method executeMethod = AsyncTask.class.getMethod("executeOnExecutor", Executor.class, Object[].class);
            executeMethod.invoke(task, executor, null);
            return;
        } catch (Exception e) {
            Log.i(TAG, "Failed to execute AsyncTask on thread pool executor, falling back to single threaded executor", e);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawBitmap!=null&&!mDrawBitmap.isRecycled()) {
            canvas.drawBitmap(mDrawBitmap, mSrcRect, mDstRect, null);
            Log.e("canvas","canvas");
        }
    }

    private void setDrawBitmap(Bitmap bitmap){
        mDrawBitmap = bitmap;
        invalidate();
    }

    public Bitmap decodeRegion() {
        //Rect sRect, int sampleSize
       // synchronized (decoderLock) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize =  mViewWidth/mImageWidth;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = mDecoder.decodeRegion(mDrawRect, options);
            if (bitmap == null) {
                throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
            }
            return bitmap;
       // }
    }

    private static class BitmapLoadTask extends AsyncTask<Void,Void,Bitmap>{
        private final WeakReference<GrabImageView> viewRef;
        private final WeakReference<BitmapRegionDecoder> decoderRef;
        private final WeakReference<Rect> rectRef;
        private int mSampleSize;
        private Exception exception;

        public BitmapLoadTask(GrabImageView grabImageView,BitmapRegionDecoder bitmapRegionDecoder,
                              Rect rect,int sampleSize){
            viewRef = new WeakReference<GrabImageView>(grabImageView);
            decoderRef = new WeakReference<BitmapRegionDecoder>(bitmapRegionDecoder);
            rectRef = new WeakReference<Rect>(rect);
            mSampleSize = sampleSize;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = mSampleSize;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = decoderRef.get().decodeRegion(rectRef.get(), options);
            if (bitmap == null) {
                exception = new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (exception==null&&bitmap!=null){
                viewRef.get().setDrawBitmap(bitmap);
            }
        }

    }
}
