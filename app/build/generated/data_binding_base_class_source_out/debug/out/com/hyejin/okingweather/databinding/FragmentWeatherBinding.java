// Generated by view binder compiler. Do not edit!
package com.hyejin.okingweather.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.hyejin.okingweather.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentWeatherBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView a;

  @NonNull
  public final TextView addrTextView;

  @NonNull
  public final TextView dateTextView;

  @NonNull
  public final TextView humidityText;

  @NonNull
  public final CircleImageView okingImage;

  @NonNull
  public final TextView okingText;

  @NonNull
  public final ImageView skyImage;

  @NonNull
  public final ImageView skyImageAfter1Hour;

  @NonNull
  public final ImageView skyImageAfter2Hour;

  @NonNull
  public final ImageView skyImageAfter3Hour;

  @NonNull
  public final ImageView skyImageAfter4Hour;

  @NonNull
  public final ImageView skyImageAfter5Hour;

  @NonNull
  public final TextView tempTextAfter1Hour;

  @NonNull
  public final TextView tempTextAfter2Hour;

  @NonNull
  public final TextView tempTextAfter3Hour;

  @NonNull
  public final TextView tempTextAfter4Hour;

  @NonNull
  public final TextView tempTextAfter5Hour;

  @NonNull
  public final TextView tempTextView;

  @NonNull
  public final TextView timeTextAfter1Hour;

  @NonNull
  public final TextView timeTextAfter2Hour;

  @NonNull
  public final TextView timeTextAfter3Hour;

  @NonNull
  public final TextView timeTextAfter4Hour;

  @NonNull
  public final TextView timeTextAfter5Hour;

  @NonNull
  public final ScrollView viewBack;

  @NonNull
  public final ImageView windImage1;

  @NonNull
  public final ImageView windImage2;

  @NonNull
  public final TextView windText;

  private FragmentWeatherBinding(@NonNull FrameLayout rootView, @NonNull TextView a,
      @NonNull TextView addrTextView, @NonNull TextView dateTextView,
      @NonNull TextView humidityText, @NonNull CircleImageView okingImage,
      @NonNull TextView okingText, @NonNull ImageView skyImage,
      @NonNull ImageView skyImageAfter1Hour, @NonNull ImageView skyImageAfter2Hour,
      @NonNull ImageView skyImageAfter3Hour, @NonNull ImageView skyImageAfter4Hour,
      @NonNull ImageView skyImageAfter5Hour, @NonNull TextView tempTextAfter1Hour,
      @NonNull TextView tempTextAfter2Hour, @NonNull TextView tempTextAfter3Hour,
      @NonNull TextView tempTextAfter4Hour, @NonNull TextView tempTextAfter5Hour,
      @NonNull TextView tempTextView, @NonNull TextView timeTextAfter1Hour,
      @NonNull TextView timeTextAfter2Hour, @NonNull TextView timeTextAfter3Hour,
      @NonNull TextView timeTextAfter4Hour, @NonNull TextView timeTextAfter5Hour,
      @NonNull ScrollView viewBack, @NonNull ImageView windImage1, @NonNull ImageView windImage2,
      @NonNull TextView windText) {
    this.rootView = rootView;
    this.a = a;
    this.addrTextView = addrTextView;
    this.dateTextView = dateTextView;
    this.humidityText = humidityText;
    this.okingImage = okingImage;
    this.okingText = okingText;
    this.skyImage = skyImage;
    this.skyImageAfter1Hour = skyImageAfter1Hour;
    this.skyImageAfter2Hour = skyImageAfter2Hour;
    this.skyImageAfter3Hour = skyImageAfter3Hour;
    this.skyImageAfter4Hour = skyImageAfter4Hour;
    this.skyImageAfter5Hour = skyImageAfter5Hour;
    this.tempTextAfter1Hour = tempTextAfter1Hour;
    this.tempTextAfter2Hour = tempTextAfter2Hour;
    this.tempTextAfter3Hour = tempTextAfter3Hour;
    this.tempTextAfter4Hour = tempTextAfter4Hour;
    this.tempTextAfter5Hour = tempTextAfter5Hour;
    this.tempTextView = tempTextView;
    this.timeTextAfter1Hour = timeTextAfter1Hour;
    this.timeTextAfter2Hour = timeTextAfter2Hour;
    this.timeTextAfter3Hour = timeTextAfter3Hour;
    this.timeTextAfter4Hour = timeTextAfter4Hour;
    this.timeTextAfter5Hour = timeTextAfter5Hour;
    this.viewBack = viewBack;
    this.windImage1 = windImage1;
    this.windImage2 = windImage2;
    this.windText = windText;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentWeatherBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentWeatherBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_weather, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentWeatherBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.a;
      TextView a = ViewBindings.findChildViewById(rootView, id);
      if (a == null) {
        break missingId;
      }

      id = R.id.addrTextView;
      TextView addrTextView = ViewBindings.findChildViewById(rootView, id);
      if (addrTextView == null) {
        break missingId;
      }

      id = R.id.dateTextView;
      TextView dateTextView = ViewBindings.findChildViewById(rootView, id);
      if (dateTextView == null) {
        break missingId;
      }

      id = R.id.humidityText;
      TextView humidityText = ViewBindings.findChildViewById(rootView, id);
      if (humidityText == null) {
        break missingId;
      }

      id = R.id.okingImage;
      CircleImageView okingImage = ViewBindings.findChildViewById(rootView, id);
      if (okingImage == null) {
        break missingId;
      }

      id = R.id.okingText;
      TextView okingText = ViewBindings.findChildViewById(rootView, id);
      if (okingText == null) {
        break missingId;
      }

      id = R.id.skyImage;
      ImageView skyImage = ViewBindings.findChildViewById(rootView, id);
      if (skyImage == null) {
        break missingId;
      }

      id = R.id.skyImageAfter1Hour;
      ImageView skyImageAfter1Hour = ViewBindings.findChildViewById(rootView, id);
      if (skyImageAfter1Hour == null) {
        break missingId;
      }

      id = R.id.skyImageAfter2Hour;
      ImageView skyImageAfter2Hour = ViewBindings.findChildViewById(rootView, id);
      if (skyImageAfter2Hour == null) {
        break missingId;
      }

      id = R.id.skyImageAfter3Hour;
      ImageView skyImageAfter3Hour = ViewBindings.findChildViewById(rootView, id);
      if (skyImageAfter3Hour == null) {
        break missingId;
      }

      id = R.id.skyImageAfter4Hour;
      ImageView skyImageAfter4Hour = ViewBindings.findChildViewById(rootView, id);
      if (skyImageAfter4Hour == null) {
        break missingId;
      }

      id = R.id.skyImageAfter5Hour;
      ImageView skyImageAfter5Hour = ViewBindings.findChildViewById(rootView, id);
      if (skyImageAfter5Hour == null) {
        break missingId;
      }

      id = R.id.tempTextAfter1Hour;
      TextView tempTextAfter1Hour = ViewBindings.findChildViewById(rootView, id);
      if (tempTextAfter1Hour == null) {
        break missingId;
      }

      id = R.id.tempTextAfter2Hour;
      TextView tempTextAfter2Hour = ViewBindings.findChildViewById(rootView, id);
      if (tempTextAfter2Hour == null) {
        break missingId;
      }

      id = R.id.tempTextAfter3Hour;
      TextView tempTextAfter3Hour = ViewBindings.findChildViewById(rootView, id);
      if (tempTextAfter3Hour == null) {
        break missingId;
      }

      id = R.id.tempTextAfter4Hour;
      TextView tempTextAfter4Hour = ViewBindings.findChildViewById(rootView, id);
      if (tempTextAfter4Hour == null) {
        break missingId;
      }

      id = R.id.tempTextAfter5Hour;
      TextView tempTextAfter5Hour = ViewBindings.findChildViewById(rootView, id);
      if (tempTextAfter5Hour == null) {
        break missingId;
      }

      id = R.id.tempTextView;
      TextView tempTextView = ViewBindings.findChildViewById(rootView, id);
      if (tempTextView == null) {
        break missingId;
      }

      id = R.id.timeTextAfter1Hour;
      TextView timeTextAfter1Hour = ViewBindings.findChildViewById(rootView, id);
      if (timeTextAfter1Hour == null) {
        break missingId;
      }

      id = R.id.timeTextAfter2Hour;
      TextView timeTextAfter2Hour = ViewBindings.findChildViewById(rootView, id);
      if (timeTextAfter2Hour == null) {
        break missingId;
      }

      id = R.id.timeTextAfter3Hour;
      TextView timeTextAfter3Hour = ViewBindings.findChildViewById(rootView, id);
      if (timeTextAfter3Hour == null) {
        break missingId;
      }

      id = R.id.timeTextAfter4Hour;
      TextView timeTextAfter4Hour = ViewBindings.findChildViewById(rootView, id);
      if (timeTextAfter4Hour == null) {
        break missingId;
      }

      id = R.id.timeTextAfter5Hour;
      TextView timeTextAfter5Hour = ViewBindings.findChildViewById(rootView, id);
      if (timeTextAfter5Hour == null) {
        break missingId;
      }

      id = R.id.viewBack;
      ScrollView viewBack = ViewBindings.findChildViewById(rootView, id);
      if (viewBack == null) {
        break missingId;
      }

      id = R.id.windImage1;
      ImageView windImage1 = ViewBindings.findChildViewById(rootView, id);
      if (windImage1 == null) {
        break missingId;
      }

      id = R.id.windImage2;
      ImageView windImage2 = ViewBindings.findChildViewById(rootView, id);
      if (windImage2 == null) {
        break missingId;
      }

      id = R.id.windText;
      TextView windText = ViewBindings.findChildViewById(rootView, id);
      if (windText == null) {
        break missingId;
      }

      return new FragmentWeatherBinding((FrameLayout) rootView, a, addrTextView, dateTextView,
          humidityText, okingImage, okingText, skyImage, skyImageAfter1Hour, skyImageAfter2Hour,
          skyImageAfter3Hour, skyImageAfter4Hour, skyImageAfter5Hour, tempTextAfter1Hour,
          tempTextAfter2Hour, tempTextAfter3Hour, tempTextAfter4Hour, tempTextAfter5Hour,
          tempTextView, timeTextAfter1Hour, timeTextAfter2Hour, timeTextAfter3Hour,
          timeTextAfter4Hour, timeTextAfter5Hour, viewBack, windImage1, windImage2, windText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
