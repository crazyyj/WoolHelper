package com.newchar.woolhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.newchar.woolhelper.R;
import com.newchar.woolhelper.entry.ActionEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author newChar
 * date 2021/6/18
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class ActionListAdapters extends BaseAdapter {

    private final ArrayList<ActionEntry> mListData;

    public ActionListAdapters() {
        mListData = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public ActionEntry getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void notifyDataSetChanged(List<ActionEntry> newData) {
        mListData.clear();
        mListData.addAll(newData);
        super.notifyDataSetChanged();
    }

    public void notifyDataAddChanged(List<ActionEntry> addData) {
        mListData.addAll(addData);
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            Context context = parent.getContext();
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_main_action_list_common, parent, false);
            viewHolder = new ViewHolder(itemView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag(R.layout.item_main_action_list_common);
        }
        updateItemView(viewHolder, mListData.get(position), position);
        return viewHolder.getItemView();
    }

    private void updateItemView(ViewHolder viewHolder, ActionEntry entry, int position) {
        viewHolder.setActionTitle(entry.getAnchorText());
        viewHolder.setActionDesc(entry.getDesc());
        viewHolder.setCheck(position % 2 == 0);
    }

    private static final class ViewHolder {

        private TextView tvActionTitle;
        private TextView tvMainItemActionDesc;
        private CheckBox cbMainItemActionSelectFlag;

        private final View itemView;

        private ViewHolder(View itemView) {
            this.itemView = itemView;
            itemView.setTag(R.layout.item_main_action_list_common, this);
            initView(itemView);
        }

        private void initView(View itemView) {
            tvActionTitle = itemView.findViewById(R.id.tvMainItemActionTitle);
            tvMainItemActionDesc = itemView.findViewById(R.id.tvMainItemActionDesc);
            cbMainItemActionSelectFlag = itemView.findViewById(R.id.cbMainItemActionSelectFlag);
        }

        private void setActionTitle(String title) {
            tvActionTitle.setText(title);
        }

        private void setActionDesc(String desc) {
            tvMainItemActionDesc.setText(desc);
        }

        private void setCheck(boolean isCheck) {
            cbMainItemActionSelectFlag.setChecked(isCheck);
        }

        private View getItemView() {
            return itemView;
        }

    }

}
