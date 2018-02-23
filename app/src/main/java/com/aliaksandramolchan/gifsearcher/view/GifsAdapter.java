package com.aliaksandramolchan.gifsearcher.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aliaksandramolchan.gifsearcher.databinding.CardGifBinding;
import com.aliaksandramolchan.gifsearcher.model.Gif;
import com.aliaksandramolchan.gifsearcher.viewmodel.GifItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class GifsAdapter extends RecyclerView.Adapter<GifsAdapter.GifItemViewHolder> {

    private List<Gif> gifs = new ArrayList<>();

    public void setGifs(List<Gif> gifs) {
        this.gifs = gifs;
        notifyDataSetChanged();
    }

    @Override
    public GifsAdapter.GifItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardGifBinding binding = CardGifBinding.inflate(inflater, parent, false);
        return new GifItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GifsAdapter.GifItemViewHolder holder, int position) {
        holder.bind(gifs.get(position));
    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }


    public static class GifItemViewHolder extends RecyclerView.ViewHolder {
        private CardGifBinding binding;

        public GifItemViewHolder(CardGifBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Gif gif) {
            if (binding.getGifItemViewModel() == null) {
                binding.setGifItemViewModel(new GifItemViewModel());
            }
            binding.getGifItemViewModel().setGif(gif);
            binding.imageView.requestLayout();

        }
    }

}
