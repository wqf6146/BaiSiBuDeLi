package com.lmm.wddog.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */

public class FoodRecipe {

    private String state;
    private ResultBean result;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {

        private String t;
        private List<ListBean> list;

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * type : 3
             * m : {"id":4799534,"t":"不可辜负的干锅\u201c食\u201d光","a":{"id":"19318768","n":"Olivia2016","p":"http://tx1.douguo.net/upload/photo/c/1/4/70_u8280582632004132453.jpg","v":0,"lv":0},"b":"http://i1.douguo.net/upload/caiku/c/7/4/c76d321b3a43f9f30f396eb38a6589c4.jpg","is":[{"i_u":"http://i1.douguo.net/upload/caiku/e/9/0/200_e9a2d9e05bb945f9aad3564a1f68d130.jpeg"},{"i_u":"http://i1.douguo.net/upload/caiku/f/2/0/200_f2ef5ea4b77c0c2b2779c30e446775c0.jpg"},{"i_u":"http://i1.douguo.net/upload/caiku/f/d/d/200_fd93ece89c33a75f2115d1fcac981ebd.jpg"},{"i_u":"http://i1.douguo.net/upload/caiku/e/1/d/200_e1af912f50f23a153784f483214970bd.jpeg"}],"c":"52"}
             */

            private int type;
            private MBean m;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public MBean getM() {
                return m;
            }

            public void setM(MBean m) {
                this.m = m;
            }

            public static class MBean implements Parcelable {

                /**
                 * id : 4799534
                 * t : 不可辜负的干锅“食”光
                 * a : {"id":"19318768","n":"Olivia2016","p":"http://tx1.douguo.net/upload/photo/c/1/4/70_u8280582632004132453.jpg","v":0,"lv":0}
                 * b : http://i1.douguo.net/upload/caiku/c/7/4/c76d321b3a43f9f30f396eb38a6589c4.jpg
                 * is : [{"i_u":"http://i1.douguo.net/upload/caiku/e/9/0/200_e9a2d9e05bb945f9aad3564a1f68d130.jpeg"},{"i_u":"http://i1.douguo.net/upload/caiku/f/2/0/200_f2ef5ea4b77c0c2b2779c30e446775c0.jpg"},{"i_u":"http://i1.douguo.net/upload/caiku/f/d/d/200_fd93ece89c33a75f2115d1fcac981ebd.jpg"},{"i_u":"http://i1.douguo.net/upload/caiku/e/1/d/200_e1af912f50f23a153784f483214970bd.jpeg"}]
                 * c : 52
                 */

                private int id;
                private String t;
                private ABean a;
                private String b;
                private String c;
                private List<IsBean> is;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getT() {
                    return t;
                }

                public void setT(String t) {
                    this.t = t;
                }

                public ABean getA() {
                    return a;
                }

                public void setA(ABean a) {
                    this.a = a;
                }

                public String getB() {
                    return b;
                }

                public void setB(String b) {
                    this.b = b;
                }

                public String getC() {
                    return c;
                }

                public void setC(String c) {
                    this.c = c;
                }

                public List<IsBean> getIs() {
                    return is;
                }

                public void setIs(List<IsBean> is) {
                    this.is = is;
                }

                public static class ABean implements Parcelable {

                    /**
                     * id : 19318768
                     * n : Olivia2016
                     * p : http://tx1.douguo.net/upload/photo/c/1/4/70_u8280582632004132453.jpg
                     * v : 0
                     * lv : 0
                     */

                    private String id;
                    private String n;
                    private String p;
                    private int v;
                    private int lv;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getN() {
                        return n;
                    }

                    public void setN(String n) {
                        this.n = n;
                    }

                    public String getP() {
                        return p;
                    }

                    public void setP(String p) {
                        this.p = p;
                    }

                    public int getV() {
                        return v;
                    }

                    public void setV(int v) {
                        this.v = v;
                    }

                    public int getLv() {
                        return lv;
                    }

                    public void setLv(int lv) {
                        this.lv = lv;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.id);
                        dest.writeString(this.n);
                        dest.writeString(this.p);
                        dest.writeInt(this.v);
                        dest.writeInt(this.lv);
                    }

                    public ABean() {
                    }

                    protected ABean(Parcel in) {
                        this.id = in.readString();
                        this.n = in.readString();
                        this.p = in.readString();
                        this.v = in.readInt();
                        this.lv = in.readInt();
                    }

                    public static final Creator<ABean> CREATOR = new Creator<ABean>() {
                        @Override
                        public ABean createFromParcel(Parcel source) {
                            return new ABean(source);
                        }

                        @Override
                        public ABean[] newArray(int size) {
                            return new ABean[size];
                        }
                    };
                }

                public static class IsBean implements Parcelable {

                    /**
                     * i_u : http://i1.douguo.net/upload/caiku/e/9/0/200_e9a2d9e05bb945f9aad3564a1f68d130.jpeg
                     */

                    private String i_u;

                    public String getI_u() {
                        return i_u;
                    }

                    public void setI_u(String i_u) {
                        this.i_u = i_u;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.i_u);
                    }

                    public IsBean() {
                    }

                    protected IsBean(Parcel in) {
                        this.i_u = in.readString();
                    }

                    public static final Creator<IsBean> CREATOR = new Creator<IsBean>() {
                        @Override
                        public IsBean createFromParcel(Parcel source) {
                            return new IsBean(source);
                        }

                        @Override
                        public IsBean[] newArray(int size) {
                            return new IsBean[size];
                        }
                    };
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.t);
                    dest.writeParcelable(this.a, flags);
                    dest.writeString(this.b);
                    dest.writeString(this.c);
                    dest.writeList(this.is);
                }

                public MBean() {
                }

                protected MBean(Parcel in) {
                    this.id = in.readInt();
                    this.t = in.readString();
                    this.a = in.readParcelable(ABean.class.getClassLoader());
                    this.b = in.readString();
                    this.c = in.readString();
                    this.is = new ArrayList<IsBean>();
                    in.readList(this.is, IsBean.class.getClassLoader());
                }

                public static final Parcelable.Creator<MBean> CREATOR = new Parcelable.Creator<MBean>() {
                    @Override
                    public MBean createFromParcel(Parcel source) {
                        return new MBean(source);
                    }

                    @Override
                    public MBean[] newArray(int size) {
                        return new MBean[size];
                    }
                };
            }
        }
    }
}
