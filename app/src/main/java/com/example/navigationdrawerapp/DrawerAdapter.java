package com.example.navigationdrawerapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final String TAG = DrawerAdapter.class.getSimpleName();
    private static final int HeaderView = 0;
    private static final int ContentView = 1;
    private static final int CollapseView = 2;

    private final DrawerListener listener;
    private final List<DrawerModel> list = new ArrayList<DrawerModel>();

    public DrawerAdapter(DrawerListener listener) {
        super();
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch(viewType) {
            case HeaderView:
                return new HeaderViewHolder (
                    DataBindingUtil.inflate(layoutInflater,
                    R.layout.cell_header, parent, false),
                    listener
                );
            case ContentView:
                return new ContentViewHolder (
                    DataBindingUtil.inflate(layoutInflater, R.layout.cell_content, parent, false),
                    listener
                );
            case CollapseView:
                return new CollapseViewHolder (
                    DataBindingUtil.inflate(layoutInflater, R.layout.cell_collapse, parent, false),
                    listener
                );
            default:
                return new CollapseViewHolder(
                    DataBindingUtil.inflate(layoutInflater, R.layout.cell_collapse, parent, false),
                    listener
                );
            }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindDataToViewHolder(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getHeader()) {
            return HeaderView;
        } else if (!list.get(position).getHeader() && list.get(position).getExpand()) {
            return ContentView;
        } else if (!list.get(position).getHeader() && !list.get(position).getExpand()) {
            return CollapseView;
        } else {
            return super.getItemViewType(position);
        }
    }

    public void setItems(List<DrawerModel> items) {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    public DrawerModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void setExpand(DrawerModel model, int position) {
        for (int index = position; index < getItemCount(); index++) {
            if (getItem(index).getHeader() && index != position) {
                break;
            } else if (!getItem(index).getHeader()) {
                getItem(index).setExpand(true);
                notifyItemChanged(index);
            } else {
                getItem(index).setExpand(true);
            }
        }
        //notifyItemRangeChanged(position, getItemCount())
    }

    public void setCompress(DrawerModel model, int position) {
        for (int index = position; index < getItemCount(); index++) {
            if (getItem(index).getHeader() && index != position) {
                break;
            } else if (!getItem(index).getHeader()) {
                getItem(index).setExpand(false);
                notifyItemChanged(index);
            } else {
                getItem(index).setExpand(false);
            }
        }
        //notifyItemRangeChanged(position, getItemCount())
    }
}