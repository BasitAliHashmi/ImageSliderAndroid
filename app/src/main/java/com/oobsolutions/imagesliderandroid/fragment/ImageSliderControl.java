package com.oobsolutions.imagesliderandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.oobsolutions.imagesliderandroid.R;
import com.oobsolutions.imagesliderandroid.adapter.ImageSliderAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageSliderControl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageSliderControl extends Fragment {

    private List<ImageView> indicaters = new ArrayList<ImageView>();

    // TODO: Rename and change types of parameters
    private ArrayList<String> images;


    public ImageSliderControl() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imagesUri Parameter 1.
     * @return A new instance of fragment ImageSliderControl.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageSliderControl newInstance(ArrayList<String> imagesUri) {
        ImageSliderControl fragment = new ImageSliderControl();
        Bundle args = new Bundle();
        args.putStringArrayList("images", imagesUri);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            images = getArguments().getStringArrayList("images");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_slider_control, container, false);
        ViewPager vp = (ViewPager) view.findViewById(R.id.slide_show_viewpager);

        setupViewPager(vp,images);
        setupCircleIndicater(view, vp, images.size());

        return view;
    }


    private void setupViewPager(ViewPager viewPager, ArrayList<String> imagesUri) {
        ImageSliderAdapter adapter = new ImageSliderAdapter(this.getContext(), imagesUri);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int j = 0; j < indicaters.size(); j++) {
                    ImageView indi = indicaters.get(j);
                    indi.setImageResource(R.drawable.slide_show_circle_indicater_blank);
                }

                indicaters.get(position).setImageResource(R.drawable.slide_show_circle_indicater_fill);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupCircleIndicater(final View view, final ViewPager viewPager, int count){
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.circleIndicaterLayout);

        for(int i=0; i<count; i++)
        {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 5, 0);

            final ImageView image = new ImageView(this.getContext());
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(20,20));
            image.setImageResource(R.drawable.slide_show_circle_indicater_blank);
            image.setLayoutParams(lp);
            image.setTag(i);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView tempimage = (ImageView) v;
                    int indexCircle = Integer.parseInt(tempimage.getTag().toString());
                    viewPager.setCurrentItem(indexCircle);
                }
            });

            // Adds the view to the layout
            layout.addView(image);

            indicaters.add(image);
        }

        indicaters.get(viewPager.getCurrentItem()).setImageResource(R.drawable.slide_show_circle_indicater_fill);

    }

}
