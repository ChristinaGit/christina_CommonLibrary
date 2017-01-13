package com.christina.common.view;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.christina.common.contract.Contracts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public class ItemSpacingDecorator extends RecyclerView.ItemDecoration {
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void getItemOffsets(
        final Rect outRect,
        final View view,
        final RecyclerView parent,
        final RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int itemCount = parent.getAdapter().getItemCount();
        final int columnCount = getColumnCount(parent);
        final int position = parent.getChildAdapterPosition(view);
        final int column = position % columnCount;
        final int rowCount = itemCount / columnCount + (itemCount % columnCount == 0 ? 0 : 1);
        final int row = position / columnCount;

        final int verticalSpacing = getVerticalSpacing();
        final int horizontalSpacing = getHorizontalSpacing();

        int bottom;
        int top;
        int left;
        int right;

        final int bottomEdge;
        final int topEdge;
        final int leftEdge;
        final int rightEdge;

        if (isBordersCollapseEnabled()) {
            bottom = verticalSpacing / 2;
            top = verticalSpacing / 2;

            left = horizontalSpacing / 2;
            right = horizontalSpacing / 2;
        } else {
            bottom = verticalSpacing;
            top = verticalSpacing;

            left = horizontalSpacing;
            right = horizontalSpacing;
        }

        if (isEdgesEnabled()) {
            bottomEdge = verticalSpacing;
            topEdge = verticalSpacing;
            leftEdge = horizontalSpacing;
            rightEdge = horizontalSpacing;
        } else {
            bottomEdge = 0;
            topEdge = 0;
            leftEdge = 0;
            rightEdge = 0;
        }

        if (position < columnCount) {
            top = topEdge;
        }

        if (row == rowCount - 1) {
            bottom = bottomEdge;
        }

        if (column == 0) {
            left = leftEdge;
        }

        if (column == columnCount - 1) {
            right = rightEdge;
        }

        outRect.bottom += bottom;
        outRect.top += top;

        outRect.left += left;
        outRect.right += right;
    }

    protected ItemSpacingDecorator(
        final int horizontalSpacing,
        final int verticalSpacing,
        final boolean bordersCollapseEnabled,
        final boolean edgesEnabled) {
        _bordersCollapseEnabled = bordersCollapseEnabled;
        _edgesEnabled = edgesEnabled;
        _horizontalSpacing = horizontalSpacing;
        _verticalSpacing = verticalSpacing;
    }

    protected int getColumnCount(@NonNull final RecyclerView parent) {
        Contracts.requireNonNull(parent, "parent == null");

        final int spanCount;

        final val layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof LinearLayoutManager) {
            spanCount = 1;
        } else {
            spanCount = 1;
        }

        return spanCount;
    }

    @Getter
    private final boolean _bordersCollapseEnabled;

    @Getter
    private final boolean _edgesEnabled;

    @Getter
    private final int _horizontalSpacing;

    @Getter
    private final int _verticalSpacing;

    @Accessors(prefix = "_")
    public static class Builder {
        @NonNull
        public final Builder collapseBorders() {
            _collapseBorders = true;

            return this;
        }

        @NonNull
        public final Builder enableEdges() {
            _enableEdges = true;

            return this;
        }

        @NonNull
        public final Builder setHorizontalSpacing(final int spacing) {
            _horizontalSpacing = spacing;

            return this;
        }

        @NonNull
        public final Builder setSpacing(final int spacing) {
            _horizontalSpacing = spacing;
            _verticalSpacing = spacing;

            return this;
        }

        @NonNull
        public final Builder setVerticalSpacing(final int spacing) {
            _verticalSpacing = spacing;

            return this;
        }

        @NonNull
        public ItemSpacingDecorator build() {
            return new ItemSpacingDecorator(getHorizontalSpacing(),
                                            getVerticalSpacing(),
                                            isCollapseBorders(),
                                            isEnableEdges());
        }

        @Getter(AccessLevel.PROTECTED)
        private boolean _collapseBorders = false;

        @Getter(AccessLevel.PROTECTED)
        private boolean _enableEdges = false;

        @Getter(AccessLevel.PROTECTED)
        private int _horizontalSpacing = 0;

        @Getter(AccessLevel.PROTECTED)
        private int _verticalSpacing = 0;
    }
}
