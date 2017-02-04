package moe.christina.common.extension;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.val;

import moe.christina.common.contract.Contracts;

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
        final int spanCount = getSpanCount(parent);
        final int spanIndex = getSpanIndex(view);
        final int position = parent.getChildAdapterPosition(view);

        final boolean isFirstColumn = spanIndex == 0;
        final boolean isLastColumn = spanIndex == spanCount - 1;
        final boolean isFirstRow = position < spanCount;
        final boolean isLastRow = position > (itemCount - spanCount - 1);

        final val padding = getPadding();
        final val spacing = getSpacing();

        final int leftSpacing = spacing.getLeft();
        final int rightSpacing = spacing.getRight();
        final int topSpacing = spacing.getTop();
        final int bottomSpacing = spacing.getBottom();

        final int leftPadding = padding.getLeft();
        final int rightPadding = padding.getRight();
        final int topPadding = padding.getTop();
        final int bottomPadding = padding.getBottom();

        int bottom;
        int top;
        int left;
        int right;

        int bottomEdge = bottomPadding;
        int topEdge = topPadding;
        int leftEdge = leftPadding;
        int rightEdge = rightPadding;

        bottom = bottomSpacing;
        top = topSpacing;

        left = leftSpacing;
        right = rightSpacing;

        if (isBordersCollapseEnabled()) {
            bottom /= 2;
            top /= 2;

            left /= 2;
            right /= 2;
        }

        if (isEdgesEnabled()) {
            bottomEdge += bottomSpacing;
            topEdge += topSpacing;
            leftEdge += leftSpacing;
            rightEdge += rightSpacing;
        }

        if (isFirstRow) {
            top = topEdge;
        }

        if (isLastRow) {
            bottom = bottomEdge;
        }

        if (isFirstColumn) {
            left = leftEdge;
        }

        if (isLastColumn) {
            right = rightEdge;
        }

        outRect.bottom += bottom;
        outRect.top += top;

        outRect.left += left;
        outRect.right += right;
    }

    protected ItemSpacingDecorator(
        @NonNull final Border padding,
        @NonNull final Border spacing,
        final boolean bordersCollapseEnabled,
        final boolean edgesEnabled) {
        Contracts.requireNonNull(padding, "padding == null");
        Contracts.requireNonNull(spacing, "spacing == null");

        _padding = padding;
        _spacing = spacing;
        _bordersCollapseEnabled = bordersCollapseEnabled;
        _edgesEnabled = edgesEnabled;
    }

    protected int getSpanCount(@NonNull final RecyclerView parent) {
        Contracts.requireNonNull(parent, "parent == null");

        final int spanCount;

        final val layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof LinearLayoutManager) {
            spanCount = 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        } else {
            spanCount = 1;
        }

        return spanCount;
    }

    protected int getSpanIndex(@NonNull final View view) {
        Contracts.requireNonNull(view, "view == null");

        final int spanIndex;

        final val layoutParams = view.getLayoutParams();
        if (layoutParams instanceof GridLayoutManager.LayoutParams) {
            spanIndex = ((GridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        } else if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            spanIndex = ((StaggeredGridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        } else if (layoutParams instanceof RecyclerView.LayoutParams) {
            spanIndex = 0;
        } else {
            spanIndex = 0;
        }

        return spanIndex;
    }

    @Getter
    private final boolean _bordersCollapseEnabled;

    @Getter
    private final boolean _edgesEnabled;

    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private final Border _padding;

    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private final Border _spacing;

    @Accessors(prefix = "_")
    @NoArgsConstructor
    @ToString(doNotUseGetters = true)
    public static class Border {
        public Border(final int left, final int top, final int right, final int bottom) {
            _left = left;
            _top = top;
            _right = right;
            _bottom = bottom;
        }

        @Getter
        @Setter
        private int _bottom;

        @Getter
        @Setter
        private int _left;

        @Getter
        @Setter
        private int _right;

        @Getter
        @Setter
        private int _top;
    }

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
        public final Builder setHorizontalPadding(final int leftPadding, final int rightPadding) {
            final val padding = getPadding();
            padding.setLeft(leftPadding);
            padding.setRight(rightPadding);

            return this;
        }

        @NonNull
        public final Builder setHorizontalSpacing(final int leftSpacing, final int rightSpacing) {
            final val spacing = getSpacing();
            spacing.setLeft(leftSpacing);
            spacing.setRight(rightSpacing);

            return this;
        }

        @NonNull
        public final Builder setPadding(final int padding) {
            setPadding(padding, padding, padding, padding);

            return this;
        }

        @NonNull
        public final Builder setPadding(
            final int leftPadding,
            final int topPadding,
            final int rightPadding,
            final int bottomPadding) {
            setVerticalPadding(topPadding, bottomPadding);
            setHorizontalPadding(leftPadding, rightPadding);

            return this;
        }

        @NonNull
        public final Builder setSpacing(final int spacing) {
            setSpacing(spacing, spacing, spacing, spacing);

            return this;
        }

        @NonNull
        public final Builder setSpacing(
            final int leftSpacing,
            final int topSpacing,
            final int rightSpacing,
            final int bottomSpacing) {
            setVerticalSpacing(topSpacing, bottomSpacing);
            setHorizontalSpacing(leftSpacing, rightSpacing);

            return this;
        }

        @NonNull
        public final Builder setVerticalPadding(final int topPadding, final int bottomPadding) {
            final val padding = getPadding();
            padding.setTop(topPadding);
            padding.setBottom(bottomPadding);

            return this;
        }

        @NonNull
        public final Builder setVerticalSpacing(final int topSpacing, final int bottomSpacing) {
            final val spacing = getSpacing();
            spacing.setTop(topSpacing);
            spacing.setBottom(bottomSpacing);

            return this;
        }

        @NonNull
        public ItemSpacingDecorator build() {
            return new ItemSpacingDecorator(getPadding(),
                                            getSpacing(),
                                            isCollapseBorders(),
                                            isEnableEdges());
        }

        @NonNull
        @Getter(AccessLevel.PROTECTED)
        private final Border _padding = new Border();

        @NonNull
        @Getter(AccessLevel.PROTECTED)
        private final Border _spacing = new Border();

        @Getter(AccessLevel.PROTECTED)
        private boolean _collapseBorders = false;

        @Getter(AccessLevel.PROTECTED)
        private boolean _enableEdges = false;
    }
}
