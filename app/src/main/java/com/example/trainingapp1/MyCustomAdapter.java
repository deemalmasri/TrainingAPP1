package com.example.trainingapp1;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MyCustomAdapter  extends ArrayAdapter {
    private List contactsInfoList;
    private Context context;

    public MyCustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.contactsInfoList = objects;
        this.context = context;
    }

    private class ViewHolder {
        TextView displayName;
        TextView phoneNumber;
        public ImageView ivPic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.contact_info, null);

            holder = new ViewHolder();
            holder.displayName = (TextView) convertView.findViewById(R.id.displayName);
            holder.phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumber);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.idIVContact);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContactsInfo contactsInfo = (ContactsInfo) contactsInfoList.get(position);
        holder.displayName.setText(contactsInfo.getDisplayName());
        holder.phoneNumber.setText(contactsInfo.getPhoneNumber());
        if(contactsInfo.getProPic() !=null){
            final String thumbnailUrl = contactsInfo.getProPic();
            final Bitmap thumbnail = fetchThumbnail(thumbnailUrl);
            holder.ivPic.setImageBitmap(thumbnail);
        } else {
            holder.ivPic.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_person));
        }

        return convertView;
    }
    private Bitmap fetchThumbnail(String image_uri) {
        Bitmap bitmap = null;
        if (image_uri != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(image_uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
