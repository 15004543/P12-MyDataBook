package sg.edu.rp.c347.p12_mydatabook;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15004543 on 10/8/2017.
 */

public class DrawerAdapter extends ArrayAdapter<String> {
    Context context;
    int layoutResourceId;
    ArrayList<String> drawerList;

    public DrawerAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<String> postList) {
        super(context, resource, postList);
        this.layoutResourceId = resource;
        this.context = context;
        this.drawerList = postList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        DrawerHolder holder = null;

        if (row == null ){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new DrawerHolder();

            holder.tvDrawer = (TextView) row.findViewById(R.id.tvDrawer);
            holder.ivDrawer = (ImageView) row.findViewById(R.id.ivDrawer);

            row.setTag(holder);

        } else {
            holder = (DrawerHolder) row.getTag();
        }

        String drawer = drawerList.get(position);

        holder.tvDrawer.setText(drawer);
        if(drawer.equalsIgnoreCase("bio")){
            holder.ivDrawer.setImageResource(android.R.drawable.ic_dialog_info);
        } else if(drawer.equalsIgnoreCase("vaccination")){
            holder.ivDrawer.setImageResource(android.R.drawable.ic_menu_edit);
        } else if(drawer.equalsIgnoreCase("anniversary")){
            holder.ivDrawer.setImageResource(android.R.drawable.ic_menu_today);
        } else if(drawer.equalsIgnoreCase("about us")){
            holder.ivDrawer.setImageResource(android.R.drawable.star_big_on);
        }
        return row;
    }
    static class DrawerHolder {
        TextView tvDrawer;
        ImageView ivDrawer;
    }
}
