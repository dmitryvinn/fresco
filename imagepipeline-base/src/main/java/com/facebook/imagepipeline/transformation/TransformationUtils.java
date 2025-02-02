/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.imagepipeline.transformation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class TransformationUtils {
  public static boolean maybeApplyTransformation(
      @Nullable BitmapTransformation transformation,
      @Nullable CloseableReference<Bitmap> bitmapReference) {
    if (transformation == null || bitmapReference == null) {
      return false;
    }
    Bitmap bitmap = bitmapReference.get();
    if (transformation.modifiesTransparency()) {
      bitmap.setHasAlpha(true);
    }
    transformation.transform(bitmap);
    return true;
  }
}
