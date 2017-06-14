package com.gmail.jorgegilcavazos.ballislife.features.posts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.jorgegilcavazos.ballislife.util.Constants;
import com.gmail.jorgegilcavazos.ballislife.R;
import com.gmail.jorgegilcavazos.ballislife.util.DateFormatUtil;
import com.squareup.picasso.Picasso;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class PostsAdapter extends BaseAdapter {
    private Context context;
    private Listing<Submission> postsList;
    private String type;
    private static LayoutInflater inflater = null;

    public PostsAdapter(Context context, Listing<Submission> postsList, String type) {
        this.context = context;
        this.postsList = postsList;
        this.type = type;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return postsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        switch (type) {
            case "small":
                rowView = inflater.inflate(R.layout.post_layout_list, parent, false);
                break;
            case "large":
                rowView = inflater.inflate(R.layout.post_layout_large, parent, false);
                break;
            default:
                rowView = inflater.inflate(R.layout.post_layout_list, parent, false);
        }

        TextView scoreView = (TextView) rowView.findViewById(R.id.scoreView);
        TextView authorView = (TextView) rowView.findViewById(R.id.authorView);
        TextView createdView = (TextView) rowView.findViewById(R.id.createdView);
        TextView titleView = (TextView) rowView.findViewById(R.id.titleView);
        TextView numOfCommentsView = (TextView) rowView.findViewById(R.id.numCommentsView);
        TextView linkView = (TextView) rowView.findViewById(R.id.linkView);

        View thumbnailContainer = rowView.findViewById(R.id.thumbnail_container);
        ImageView thumbnail = (ImageView) rowView.findViewById(R.id.thumbnail);
        ImageView thumbnailType = (ImageView) rowView.findViewById(R.id.thumbnail_content_type);

        Submission post = postsList.get(position);

        titleView.setText(post.getTitle());
        if (post.isStickied()) {
            titleView.setTextColor(ContextCompat.getColor(context, R.color.stickiedColor));
            titleView.setTypeface(null, Typeface.BOLD);
        }
        scoreView.setText(String.valueOf(post.getScore()));
        authorView.setText(post.getAuthor());
        createdView.setText(DateFormatUtil.formatRedditDate(post.getCreated()));
        numOfCommentsView.setText(String.valueOf(post.getCommentCount()));

        String thumbnailUrl = post.getThumbnail();

        if (post.isSelfPost()) {
            linkView.setText("• self." + post.getSubredditName());
            thumbnailContainer.setVisibility(View.GONE);
        } else {
            String domain = post.getDomain();
            linkView.setText("• " + domain);
            if (thumbnailUrl != null) {
                Picasso.with(context).load(thumbnailUrl).into(thumbnail);
                if (domain.equals(Constants.YOUTUBE_DOMAIN)
                        || domain.equals(Constants.INSTAGRAM_DOMAIN)
                        || domain.equals(Constants.STREAMABLE_DOMAIN)) {
                    thumbnailType.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                } else if (domain.equals(Constants.IMGUR_DOMAIN )
                        || domain.equals(Constants.GIPHY_DOMAIN)) {
                    thumbnailType.setImageResource(R.drawable.ic_gif_black_24dp);
                } else {
                    thumbnailType.setVisibility(View.GONE);
                }
            } else {
                thumbnailContainer.setVisibility(View.GONE);
            }
        }

        return rowView;
    }

}
