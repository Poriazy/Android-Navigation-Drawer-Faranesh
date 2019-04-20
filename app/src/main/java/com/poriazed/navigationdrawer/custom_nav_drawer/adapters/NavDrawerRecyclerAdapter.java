package com.poriazed.navigationdrawer.custom_nav_drawer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.poriazed.navigationdrawer.R;
import com.poriazed.navigationdrawer.custom_nav_drawer.OnNavViewClickListener;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerDivider;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerEntry;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerWithIcon;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerWithOutIcon;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerWithToggle;

import java.util.List;

public class NavDrawerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "NavDrawerRecyclerAdapte";

    private List<NavDrawerEntry> drawerEntries;
    private LayoutInflater inflater;
    private OnNavViewClickListener clickListener;

    public NavDrawerRecyclerAdapter(List<NavDrawerEntry> drawerEntries, Context context) {
        this.drawerEntries = drawerEntries;
        inflater = LayoutInflater.from(context);
    }

    public void setClickListener(OnNavViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: called.");

        if (drawerEntries.get(position) instanceof NavDrawerDivider)
            return 0;
        if (drawerEntries.get(position) instanceof NavDrawerWithOutIcon)
            return 1;
        if (drawerEntries.get(position) instanceof NavDrawerWithIcon)
            return 2;
        if (drawerEntries.get(position) instanceof NavDrawerWithToggle)
            return 3;

        Log.d(TAG, "getItemViewType: finished with error");
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: started.");
        View itemLayoutView;
        switch (viewType) {
            case 0:
                itemLayoutView = inflater.inflate(R.layout.item_nav_drawer_divider, viewGroup, false);
                DividerVh vh = new DividerVh(itemLayoutView);
                return vh;

            case 1:
                itemLayoutView = inflater.inflate(R.layout.item_nav_drawer_with_out_icon, viewGroup, false);
                ItemVh itemVh = new ItemVh(itemLayoutView);
                return itemVh;

            case 2:
                itemLayoutView = inflater.inflate(R.layout.item_nav_drawer_with_icon, viewGroup, false);
                ItemIconVh itemIconVh = new ItemIconVh(itemLayoutView);
                return itemIconVh;

            case 3:
                itemLayoutView = inflater.inflate(R.layout.item_nav_drawer_with_toggle, viewGroup, false);
                ToggleVH toggleVH = new ToggleVH(itemLayoutView);
                return toggleVH;
        }
        Log.d(TAG, "onCreateViewHolder: finished with error");
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final NavDrawerEntry entry = drawerEntries.get(position);

        if (entry instanceof NavDrawerWithOutIcon) {
            final ItemVh vh = (ItemVh) viewHolder;
            vh.title.setText(((NavDrawerWithOutIcon) entry).getTitle());
            vh.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onItemClick(((NavDrawerWithOutIcon) entry).getTitle());
                    }
                }
            });
        }

        if (entry instanceof NavDrawerWithIcon) {
            final ItemIconVh vh = (ItemIconVh) viewHolder;
            vh.mIcon.setBackgroundResource(((NavDrawerWithIcon) entry).getIcon());
            vh.mTitle.setText(((NavDrawerWithIcon) entry).getTitle());
            vh.mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener!=null)
                        clickListener.onItemClick(((NavDrawerWithIcon) entry).getTitle());
                }
            });
        }

        if (entry instanceof NavDrawerWithToggle) {
            final ToggleVH vh = (ToggleVH) viewHolder;
            vh.mTitle.setText(((NavDrawerWithToggle) entry).getTitle());
            vh.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (clickListener != null)
                        clickListener.onSwitchStateChange(isChecked);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return drawerEntries.size();
    }

    // view holder classes -----------------------------------------------------------------------
    class DividerVh extends RecyclerView.ViewHolder {
        public DividerVh(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ItemVh extends RecyclerView.ViewHolder {
        final LinearLayout parent;
        final TextView title;
        public ItemVh(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.nav_item_parent);
            title = itemView.findViewById(R.id.nav_item_title);
        }
    }

    class ItemIconVh extends RecyclerView.ViewHolder {
        final LinearLayout mParent;
        final ImageView mIcon;
        final TextView mTitle;

        public ItemIconVh(@NonNull View itemView) {
            super(itemView);
            mParent = itemView.findViewById(R.id.nav_item_parent);
            mIcon = itemView.findViewById(R.id.nav_item_image);
            mTitle = itemView.findViewById(R.id.nav_item_title);
        }
    }

    class ToggleVH extends RecyclerView.ViewHolder {
        final RelativeLayout mParent;
        final TextView mTitle;
        final Switch mSwitch;

        public ToggleVH(View itemView) {
            super(itemView);
            mParent = itemView.findViewById(R.id.nav_item_parent);
            mTitle = itemView.findViewById(R.id.nav_item_title);
            mSwitch = itemView.findViewById(R.id.nav_switch);
        }
    }
}
