package com.christina.common.extension.delegate;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;

import com.christina.common.R;
import com.christina.common.extension.view.ContentLoaderProgressBar;
import com.christina.common.utility.AnimationViewUtils;

@Accessors(prefix = "_")
public class LoadingViewDelegate {
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public final void invalidateContentView() {
        final val contentView = getContentView();
        final val noContentView = getNoContentView();

        if (isContentVisible()) {
            if (hasContent()) {
                if (contentView != null) {
                    AnimationViewUtils.animateSetVisibility(contentView,
                                                            View.VISIBLE,
                                                            R.anim.fade_in_long);
                }
                if (noContentView != null) {
                    noContentView.setVisibility(View.GONE);
                }
            } else {
                if (noContentView != null) {
                    noContentView.setVisibility(View.VISIBLE);
                }
                if (contentView != null) {
                    contentView.setVisibility(View.GONE);
                }
            }
        } else {
            if (contentView != null) {
                contentView.setVisibility(View.GONE);
            }
            if (noContentView != null) {
                noContentView.setVisibility(View.GONE);
            }
        }
    }

    public final void invalidateErrorView() {
        final val errorView = getErrorView();
        if (errorView != null) {
            if (isErrorVisible()) {
                errorView.setVisibility(View.VISIBLE);
            } else {
                errorView.setVisibility(View.GONE);
            }
        }
    }

    public final void invalidateLoadingView() {
        final val loadingView = getLoadingView();
        if (loadingView != null) {
            if (isLoadingVisible()) {
                loadingView.show();
            } else {
                loadingView.hide();
            }
        }
    }

    public final void setContentVisible(final boolean visible) {
        if (_contentVisible != visible) {
            _contentVisible = visible;

            onContentVisibilityChanged();
        }
    }

    public final void setErrorVisible(final boolean visible) {
        if (_errorVisible != visible) {
            _errorVisible = visible;

            onErrorVisibilityChanged();
        }
    }

    public final void setHasContent(final boolean hasContent) {
        if (_hasContent != hasContent) {
            _hasContent = hasContent;

            onContentChanged();
        }
    }

    public final void setLoadingVisible(final boolean visible) {
        if (_loadingVisible != visible) {
            _loadingVisible = visible;

            onLoadingVisibilityChanged();
        }
    }

    @CallSuper
    public void hideAll() {
        setContentVisible(false);
        setLoadingVisible(false);
        setErrorVisible(false);
    }

    @CallSuper
    public void invalidateViews() {
        invalidateContentView();
        invalidateLoadingView();
        invalidateErrorView();
    }

    @CallSuper
    public void resetLoading() {
        final val loadingView = getLoadingView();
        if (loadingView != null) {
            loadingView.reset(isLoadingVisible());
        }
    }

    @CallSuper
    public void showContent(final boolean hasContent) {
        setHasContent(hasContent);
        setContentVisible(true);
        setLoadingVisible(false);
        setErrorVisible(false);
    }

    @CallSuper
    public void showContent() {
        showContent(true);
    }

    @CallSuper
    public void showError() {
        setContentVisible(false);
        setLoadingVisible(false);
        setErrorVisible(true);
    }

    @CallSuper
    public void showLoading() {
        setContentVisible(false);
        setLoadingVisible(true);
        setErrorVisible(false);
    }

    @CallSuper
    protected void onContentChanged() {
        invalidateContentView();
    }

    @CallSuper
    protected void onContentVisibilityChanged() {
        invalidateContentView();
    }

    @CallSuper
    protected void onErrorVisibilityChanged() {
        invalidateErrorView();
    }

    @CallSuper
    protected void onLoadingVisibilityChanged() {
        invalidateLoadingView();
    }

    @Getter
    @Setter
    @Nullable
    private View _contentView;

    @Getter
    private boolean _contentVisible;

    @Getter
    @Setter
    @Nullable
    private View _errorView;

    @Getter
    private boolean _errorVisible;

    @Getter
    @Accessors(fluent = true, prefix = "_")
    private boolean _hasContent;

    @Getter
    @Setter
    @Nullable
    private ContentLoaderProgressBar _loadingView;

    @Getter
    private boolean _loadingVisible;

    @Getter
    @Setter
    @Nullable
    private View _noContentView;

    public static class Builder {
        @NonNull
        public LoadingViewDelegate build() {
            final val loadingViewDelegate = new LoadingViewDelegate();

            loadingViewDelegate.setContentView(_contentView);
            loadingViewDelegate.setErrorView(_errorView);
            loadingViewDelegate.setNoContentView(_noContentView);
            loadingViewDelegate.setLoadingView(_loadingView);
            loadingViewDelegate.hideAll();

            return loadingViewDelegate;
        }

        @NonNull
        public Builder setContentView(@Nullable final View contentView) {
            _contentView = contentView;

            return this;
        }

        @NonNull
        public Builder setErrorView(@Nullable final View errorView) {
            _errorView = errorView;

            return this;
        }

        @NonNull
        public Builder setLoadingView(@Nullable final ContentLoaderProgressBar loadingView) {
            _loadingView = loadingView;

            return this;
        }

        @NonNull
        public Builder setNoContentView(@Nullable final View noContentView) {
            _noContentView = noContentView;

            return this;
        }

        @Nullable
        private View _contentView;

        @Nullable
        private View _errorView;

        @Nullable
        private ContentLoaderProgressBar _loadingView;

        @Nullable
        private View _noContentView;
    }
}
