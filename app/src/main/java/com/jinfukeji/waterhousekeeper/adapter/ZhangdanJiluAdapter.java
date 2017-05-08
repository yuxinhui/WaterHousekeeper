package com.jinfukeji.waterhousekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jinfukeji.waterhousekeeper.R;
import com.jinfukeji.waterhousekeeper.been.ZhangdanJiluBeen;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by "于志渊"
 * 时间:"9:26"
 * 包名:com.jinfukeji.waterhousekeeper.adapter
 * 描述:账单充值记录适配器
 */

public class ZhangdanJiluAdapter extends BaseAdapter {
    private ArrayList<ZhangdanJiluBeen.MessageBean> arrayList=new ArrayList<ZhangdanJiluBeen.MessageBean>();
    private Context context;

    public ZhangdanJiluAdapter(ArrayList<ZhangdanJiluBeen.MessageBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public int getCount() {
        if (arrayList != null){
            return arrayList.size();
        }
        return 0;
    }

    @Override
    public ZhangdanJiluBeen.MessageBean getItem(int i) {
        if (arrayList != null){
            return arrayList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_zhangdanjilu,null);
            viewHolder.item_jilu_payway_tv= (TextView) view.findViewById(R.id.item_jilu_payway_tv);
            viewHolder.item_jilu_date_tv= (TextView) view.findViewById(R.id.item_jilu_date_tv);
            viewHolder.item_jilu_money_tv= (TextView) view.findViewById(R.id.item_jilu_money_tv);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final ZhangdanJiluBeen.MessageBean messageBean=getItem(i);
        int money=messageBean.getMoney();
        DecimalFormat df=new DecimalFormat("0.00");
        viewHolder.item_jilu_money_tv.setText("-"+df.format(money));
        if ("0".equals(messageBean.getPayway())){
            viewHolder.item_jilu_payway_tv.setText("微信支付");
        }else if ("1".equals(messageBean.getPayway())){
            viewHolder.item_jilu_payway_tv.setText("支付宝支付");
        }
        long currentTime=messageBean.getRechargeDate();
        Date dateOld=new Date(currentTime);
        String sDateTime="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        sDateTime=sdf.format(dateOld);
        viewHolder.item_jilu_date_tv.setText(sDateTime);
        return view;
    }
    //控件存放
    public class ViewHolder{
        TextView item_jilu_payway_tv,item_jilu_date_tv,item_jilu_money_tv;
    }
}
