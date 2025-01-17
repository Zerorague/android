// Generated by view binder compiler. Do not edit!
package cl.cfuentes.prueba3aplicacionesmoviles.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import cl.cfuentes.prueba3aplicacionesmoviles.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AnimalesItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView btDelete;

  @NonNull
  public final ImageView btUpdate;

  @NonNull
  public final TextView tvChipAnimal;

  @NonNull
  public final TextView tvnombreAnimal;

  private AnimalesItemBinding(@NonNull CardView rootView, @NonNull ImageView btDelete,
      @NonNull ImageView btUpdate, @NonNull TextView tvChipAnimal,
      @NonNull TextView tvnombreAnimal) {
    this.rootView = rootView;
    this.btDelete = btDelete;
    this.btUpdate = btUpdate;
    this.tvChipAnimal = tvChipAnimal;
    this.tvnombreAnimal = tvnombreAnimal;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static AnimalesItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AnimalesItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.animales_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AnimalesItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btDelete;
      ImageView btDelete = ViewBindings.findChildViewById(rootView, id);
      if (btDelete == null) {
        break missingId;
      }

      id = R.id.btUpdate;
      ImageView btUpdate = ViewBindings.findChildViewById(rootView, id);
      if (btUpdate == null) {
        break missingId;
      }

      id = R.id.tvChipAnimal;
      TextView tvChipAnimal = ViewBindings.findChildViewById(rootView, id);
      if (tvChipAnimal == null) {
        break missingId;
      }

      id = R.id.tvnombreAnimal;
      TextView tvnombreAnimal = ViewBindings.findChildViewById(rootView, id);
      if (tvnombreAnimal == null) {
        break missingId;
      }

      return new AnimalesItemBinding((CardView) rootView, btDelete, btUpdate, tvChipAnimal,
          tvnombreAnimal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
