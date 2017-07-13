package com.tan.lgy.testrecyclerview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder>{

	private List<Bean> beans = null;
	private Context mContext = null;
	private int i = 0;
	private DelAndAddListner delAndAddListner = null;

	public List<Bean> getBeans() {
		return beans;
	}
	public void setBeans(List<Bean> beans) {
		this.beans = beans;
	}
	public MyAdapter(Context context){
		this.mContext = context;
	}

	@Override
	public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
		Holder vh = new Holder(view);
		return vh;
	}



	@Override
	public void onBindViewHolder(final Holder holder, final int position) {
		if (beans!=null&&beans.size()>0)
		{
			holder.tv_name.setText(beans.get(position).getName());
			holder.tv_age.setText(""+beans.get(position).getAge());
			holder.tv_sex.setText(beans.get(position).getSex());
			holder.bt_del.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (delAndAddListner!=null)
					{
						Toast.makeText(mContext,"position:"+position+" name:"+beans.get(position).getName(),Toast.LENGTH_SHORT).show();
						delAndAddListner.delAction();
						removeItem(holder);
					}
				}
			});
		}

	}

	@Override
	public int getItemCount() {
		return beans==null?0:beans.size();
	}

	public class Holder extends RecyclerView.ViewHolder{
		TextView tv_name;
		TextView tv_age;
		TextView tv_sex;
		Button bt_del;
		public Holder(View v)
		{
			super(v);
			tv_name = (TextView) v.findViewById(R.id.name);
			tv_age = (TextView) v.findViewById(R.id.age);
			tv_sex = (TextView) v.findViewById(R.id.sex);
			bt_del = (Button) v.findViewById(R.id.del);
		}
	}

	public interface DelAndAddListner
	{
		public void delAction();
	}

	public void setDelAndAddListner(DelAndAddListner delAndAddListner)
	{
		this.delAndAddListner = delAndAddListner;
	}


	/*
	*如果以这种方式就不会出现点击某item的删除键，删除的却不是本身的情况
	 */
	private void removeItem(Holder holder)
	{
		int position = holder.getLayoutPosition();
		beans.remove(position);
		notifyItemRemoved(position);
	}
	/**
	 * 如果以这种方式来处理点击的删除事件，beans的list并没有刷新，会造成你点击的item的删除按钮并不会删除当前的item,删除的是其他的item
	 * 如果加上notifyDataSetChanged()会刷新list,就可以避免上面的情况，但是这样做就没有动画效果
	 */
	public void removeItem2(Bean bean) {
		int position = beans.indexOf(bean);
		beans.remove(position);
		notifyItemRemoved(position);//Attention!
		//notifyDataSetChanged();
	}

	public void addItem(Bean content) {
		beans.add(0, content);
		notifyItemInserted(0); //Attention!
	}
}
