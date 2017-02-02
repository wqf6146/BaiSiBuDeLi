package com.lmm.wddog.http.imagemodel;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.lmm.wddog.bean.JjBean;
import com.lmm.wddog.ui.view.ImageLoadingDrawable;
import com.lmm.wddog.util.fresco.FrescoUtils;
import com.lmm.wddog.util.fresco.MatrixScaleType;

/**
 * Created by Administrator on 2017/1/22.
 */

public class ImageViewModel {

    @BindingAdapter({"bind:godimageUrl"})
    public static void lloadImage(SimpleDraweeView draweeView, String url) {
        draweeView.setImageURI(Uri.parse(url));
    }
    @BindingAdapter({"bind:loadGif"})
    public static void loadGif(SimpleDraweeView draweeView, String url) {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(draweeView.getContext().getResources());
        GenericDraweeHierarchy hierarchy = builder.build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(url))
                .setAutoPlayAnimations(true)
                .build();
        hierarchy.setProgressBarImage(new ImageLoadingDrawable(), ScalingUtils.ScaleType.CENTER_INSIDE);
        draweeView.setAspectRatio(1);
        draweeView.setHierarchy(hierarchy);
        draweeView.setController(controller);
    }

    @BindingAdapter({"bind:largeimageUrl"})
    public static void loadLargeImage(SimpleDraweeView draweeView, String url) {
        ImageRequest ir = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(ir)
                .build();
        GenericDraweeHierarchy hierarchy = draweeView.getHierarchy();
        hierarchy.setProgressBarImage(new ImageLoadingDrawable(), ScalingUtils.ScaleType.CENTER_INSIDE);
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
        hierarchy.setActualImageFocusPoint(new PointF(0,0));
        draweeView.setHierarchy(hierarchy);
        draweeView.setController(controller);
    }

    @BindingAdapter({"bind:imageData"})
    public static void loadImage(final SimpleDraweeView draweeView, JjBean.ListBean object) {

        if (object.getType().equals("gif")) {
            if (object.getGif().getImages().size() != 0) {
                String url = object.getGif().getImages().get(0);
                GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(draweeView.getContext().getResources());
                GenericDraweeHierarchy hierarchy = builder.build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse(url))
                        .setAutoPlayAnimations(true)
                        .build();
                hierarchy.setProgressBarImage(new ImageLoadingDrawable(), ScalingUtils.ScaleType.CENTER_INSIDE);
                draweeView.setAspectRatio((float) object.getGif().getWidth() / object.getGif().getHeight());
                draweeView.setHierarchy(hierarchy);
                draweeView.setController(controller);
            }
        }else{
                GenericDraweeHierarchy hierarchy = draweeView.getHierarchy();
                hierarchy.setProgressBarImage(new ImageLoadingDrawable(), ScalingUtils.ScaleType.CENTER_INSIDE);
                draweeView.setHierarchy(hierarchy);
                draweeView.setAspectRatio((float) object.getImage().getWidth()/object.getImage().getHeight());
                draweeView.setImageURI(Uri.parse(object.getImage().getBig().get(0)));
        }
    }
}
