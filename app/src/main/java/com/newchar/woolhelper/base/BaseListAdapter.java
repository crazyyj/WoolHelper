package com.newchar.woolhelper.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NewLq1
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected List<T> mAdapterData;

    public static final int FLAG_LOAD_ADAPTER_DATA = 0;
    public static final int FLAG_MORE_ADAPTER_DATA = 1;

    public BaseListAdapter() {
        super();
        this.mAdapterData = new ArrayList<>();
    }

    public BaseListAdapter(List<T> adapterData) {
        super();
        this.mAdapterData = adapterData;
    }

    /**
     * 刷新列表数据
     *
     * @param adapterData 更新的新数据
     */
    public void notifyDataSetChanged(List<T> adapterData) {
        notifyDataSetChanged(adapterData, FLAG_LOAD_ADAPTER_DATA);
    }

    /**
     * 更新列表数据
     *
     * @param adapterData 更新的新数据
     * @param flag        {@link #FLAG_LOAD_ADAPTER_DATA}
     *                    {@link #FLAG_MORE_ADAPTER_DATA}
     */
    public void notifyDataSetChanged(List<T> adapterData, int flag) {
        if (adapterData == null || adapterData.isEmpty()) {
            this.mAdapterData.clear();
        } else {
            if (flag == FLAG_LOAD_ADAPTER_DATA) {
                this.mAdapterData.clear();
                this.mAdapterData.addAll(adapterData);
            } else if (flag == FLAG_MORE_ADAPTER_DATA) {
                this.mAdapterData.addAll(adapterData);
            }
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAdapterData == null ? 0 : mAdapterData.size();
    }

    @Override
    public Object getItem(int position) {
        return mAdapterData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapterData == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseAdapterViewHolder<T> holder;
        if (convertView == null) {
            holder = getBaseHolder();
            convertView = View.inflate(parent.getContext(), holder.getLayoutId(), null);
            holder.initWidgets(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BaseAdapterViewHolder<T>) convertView.getTag();
        }
        holder.setData(mAdapterData, position);
        return convertView;
    }

    protected abstract BaseAdapterViewHolder<T> getBaseHolder();

}
