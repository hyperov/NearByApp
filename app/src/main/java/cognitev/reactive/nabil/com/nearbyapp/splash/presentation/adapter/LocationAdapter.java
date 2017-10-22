package cognitev.reactive.nabil.com.nearbyapp.splash.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cognitev.reactive.nabil.com.nearbyapp.R;
import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashViewModel;

/**
 * Created by anabil on 10/21/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_VIEW_TYPE = 0;
    public static final int PROGRESS_ITEM_VIEW_TYPE = 1;
    private boolean showLoader;

    private List<SplashViewModel> splashViewModels;
    private Context context;

    public LocationAdapter(List<SplashViewModel> splashViewModels, Context context) {
        this.splashViewModels = splashViewModels;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == PROGRESS_ITEM_VIEW_TYPE) {
            view = inflater.inflate(R.layout.progress_bar_item_loading, parent, false);
            return new ProgressViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.list_item, parent, false);
            return new LocationViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        LocationViewHolder locationViewHolder;
        if (holder instanceof ProgressViewHolder) {
            ProgressViewHolder loaderViewHolder = (ProgressViewHolder) holder;
            if (showLoader) {
                loaderViewHolder.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                loaderViewHolder.mProgressBar.setVisibility(View.GONE);
            }
            return;
        } else {

            locationViewHolder = (LocationViewHolder) holder;
        }

        locationViewHolder.title.setText(splashViewModels.get(position).getName());
        locationViewHolder.address.setText(splashViewModels.get(position).getAddress());

        String imageUrl = splashViewModels.get(position).getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty())
            Picasso.with(context).load(imageUrl).into(((LocationViewHolder) holder).locationImage);
//        locationViewHolder.address.setText(splashViewModels.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        if (splashViewModels.isEmpty())
            return 0;
        return splashViewModels.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1)
            return PROGRESS_ITEM_VIEW_TYPE;
        else
            return ITEM_VIEW_TYPE;


    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressBar mProgressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView title;
        TextView address;
        ImageView locationImage;


        public LocationViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.title);
            address = (TextView) view.findViewById(R.id.address);
            locationImage = (ImageView) view.findViewById(R.id.image_list);

        }

        @Override
        public String toString() {
            return super.toString();//+ " '" + mContentView.getText() + "'";
        }
    }

    public void showLoading(boolean status) {
        showLoader = status;
    }

    public boolean isLoading() {
        return showLoader;
    }
}
