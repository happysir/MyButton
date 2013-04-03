package com.example.myan;

import android.R.anim;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class JMQButton extends RelativeLayout{

    private Context context;  
    private int leftMargin=0,bottomMargin=0;  
    private final int buttonWidth=58;//图片宽高  
    private final int r=180;//半径  
    private final int maxTimeSpent=200;//最长动画耗时  
    private final int minTimeSpent=80;//最短动画耗时  
    private int intervalTimeSpent;//每相邻2个的时间间隔  
    private Button[] btns;  
    private Button btn_menu;  
    private RelativeLayout.LayoutParams params;  
    private boolean isOpen = false;//是否菜单打开状态  
    private float angle;//每个按钮之间的夹角  
    public JMQButton(Context context) {  
        super(context);  
        // TODO Auto-generated constructor stub  
        this.context=context;  
    }  
    public JMQButton(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
        this.context=context;  
    }  
      
    @Override  
    protected void onFinishInflate() {  
        // TODO Auto-generated method stub  
        super.onFinishInflate();  
        View view=LayoutInflater.from(context).inflate(R.layout.anim_buttons, this);  
          
        initButtons(view);  
          
    }  
  
    private void initButtons(View view){  
        // TODO Auto-generated method stub  
        //6个按钮，具体视情况而定  
        btns=new Button[6];  
        btns[0] = (Button) view.findViewById(R.id.btn_01);  
        btns[1] = (Button) view.findViewById(R.id.btn_02);  
        btns[2] = (Button) view.findViewById(R.id.btn_03);  
        btns[3] = (Button) view.findViewById(R.id.btn_04);  
        btns[4] = (Button) view.findViewById(R.id.btn_05);  
        btns[5] = (Button) view.findViewById(R.id.btn_06);  
        btn_menu = (Button) view.findViewById(R.id.btn_menu);  
//          
        leftMargin=((RelativeLayout.LayoutParams)(btn_menu.getLayoutParams())).leftMargin;  
        bottomMargin=((RelativeLayout.LayoutParams)(btn_menu.getLayoutParams())).bottomMargin;  
          
        for(int i=0;i<btns.length;i++){  
            btns[i].setLayoutParams(btn_menu.getLayoutParams()); 
            btns[i].setTag(String.valueOf(i));  
            btns[i].setOnClickListener(clickListener);  
        }  
          
        intervalTimeSpent=(maxTimeSpent-minTimeSpent)/btns.length;  
        angle=(float)Math.PI/(2*(btns.length-1));  
    }  
      
    @Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
       
        super.onSizeChanged(w, h, oldw, oldh);  
        final int bottomMargins=this.getMeasuredHeight()-buttonWidth-bottomMargin;  
 
        btn_menu.setOnClickListener(new OnClickListener() {  
              
            @Override  
            public void onClick(View v) {  
                               
                if(!isOpen){  
                    isOpen = true;  
//                  btn_menu.startAnimation(animRotate(-45.0f, 0.5f, 0.45f));  
                    for(int i=0;i<btns.length;i++){  
                        float xLenth=(float)(r*Math.sin(i*angle));  
                        float yLenth=(float)(r*Math.cos(i*angle));  
//                      Log.i("tag", "xLenth======"+xLenth+",yLenth======"+yLenth);  
                        btns[i].startAnimation(animTranslate(xLenth, -yLenth, leftMargin+(int)xLenth, bottomMargins - (int)yLenth, btns[i], minTimeSpent+i*intervalTimeSpent));  
                    }  
                      
                }  
                else{                     
                    isOpen = false;  
//                  btn_menu.startAnimation(animRotate(90.0f, 0.5f, 0.45f));  
                    for(int i=0;i<btns.length;i++){  
                        float xLenth=(float)(r*Math.sin(i*angle));  
                        float yLenth=(float)(r*Math.cos(i*angle));  
//                      Log.i("tag", "xLenth======"+xLenth+",yLenth======"+yLenth);  
                        btns[i].startAnimation(animTranslate(-xLenth, yLenth, leftMargin, bottomMargins, btns[i], maxTimeSpent-i*intervalTimeSpent));  
                    }  
                }  
                      
            }  
        });  
          
    }  
    private Animation animScale(float toX, float toY){  
        // TODO Auto-generated method stub  
        Animation animation = new ScaleAnimation(1.0f, toX, 1.0f, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);  
        animation.setInterpolator(context, anim.accelerate_decelerate_interpolator);  
        animation.setDuration(400);  
        animation.setFillAfter(false);  
        return animation;  
          
    }  
      
    private Animation animRotate(float toDegrees, float pivotXValue, float pivotYValue){  
        // TODO Auto-generated method stub  
        final Animation animation = new RotateAnimation(0, toDegrees, Animation.RELATIVE_TO_SELF, pivotXValue, Animation.RELATIVE_TO_SELF, pivotYValue);  
        animation.setAnimationListener(new AnimationListener(){  
              
            @Override  
            public void onAnimationStart(Animation animation){  
                // TODO Auto-generated method stub  
                  
            }  
              
            @Override  
            public void onAnimationRepeat(Animation animation){  
                // TODO Auto-generated method stub  
                  
            }  
              
            @Override  
            public void onAnimationEnd(Animation animation){  
                // TODO Auto-generated method stub  
                animation.setFillAfter(true);  
            }  
        });  
        return animation;  
    }  
      
      
    private Animation animTranslate(float toX, float toY, final int lastX, final int lastY,  
            final Button button, long durationMillis){  
        // TODO Auto-generated method stub  
        Animation animation = new TranslateAnimation(0, toX, 0, toY);                 
        animation.setAnimationListener(new AnimationListener(){  
                          
            @Override  
            public void onAnimationStart(Animation animation){  
                // TODO Auto-generated method stub  
                                  
            }  
                          
            @Override  
            public void onAnimationRepeat(Animation animation) {  
                // TODO Auto-generated method stub  
                              
            }  
                          
            @Override  
            public void onAnimationEnd(Animation animation){  
                // TODO Auto-generated method stub  
                params = new RelativeLayout.LayoutParams(0, 0);  
                params.height = buttonWidth;  
                params.width = buttonWidth;                                           
                params.setMargins(lastX, lastY, 0, 0);  
                button.setLayoutParams(params);  
                button.clearAnimation();  
                          
            }  
        });                                                                                               
        animation.setDuration(durationMillis);  
        return animation;  
    }  
      
    View.OnClickListener clickListener=new View.OnClickListener(){  
  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
            int selectedItem=Integer.parseInt((String)v.getTag());  
            for(int i=0;i<btns.length;i++){  
                if(i==selectedItem){  
                    btns[i].startAnimation(animScale(2.0f, 2.0f));  
                }else{  
                    btns[i].startAnimation(animScale(0.0f, 0.0f));  
                }  
            }  
            if(onButtonClickListener!=null){  
                onButtonClickListener.onButtonClick(v, selectedItem);  
            }  
        }  
          
    };  
      
    public boolean isOpen(){  
        return isOpen;  
    }  
      
    private OnButtonClickListener onButtonClickListener;  
    public interface OnButtonClickListener{  
        void onButtonClick(View v,int id);  
    }  
    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){  
        this.onButtonClickListener=onButtonClickListener;  
    }  
} 
