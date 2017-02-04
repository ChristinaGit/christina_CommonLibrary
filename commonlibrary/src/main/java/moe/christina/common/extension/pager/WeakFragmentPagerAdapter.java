package moe.christina.common.extension.pager;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;

import moe.christina.common.contract.Contracts;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

@Accessors(prefix = "_")
public abstract class WeakFragmentPagerAdapter extends FragmentPagerAdapter {

    @Override
    public int getCount() {
        final int count;

        final val pageFactory = getPageFactory();
        if (pageFactory != null) {
            count = pageFactory.getPageCount();
        } else {
            count = 0;
        }

        return count;
    }

    @Nullable
    public Fragment getFragment(final int position) {
        final Fragment result;

        final Reference<Fragment> fragmentRef = getFragments().get(position);

        if (fragmentRef != null) {
            result = fragmentRef.get();
        } else {
            result = null;
        }

        return result;
    }

    @Override
    public Fragment getItem(final int position) {
        Contracts.requireInRange(position, 0, getCount() - 1, new IndexOutOfBoundsException());

        final Fragment pageFragment;

        final val pageFactory = getPageFactory();

        if (pageFactory != null) {
            pageFragment = pageFactory.createPageFragment(position);
        } else {
            pageFragment = null;
        }

        return pageFragment;
    }

    @CallSuper
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Object item = super.instantiateItem(container, position);

        if (item instanceof Fragment) {
            final val fragment = (Fragment) item;

            onInstantiateFragment(fragment, position);
        }

        return item;
    }

    @CallSuper
    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        super.destroyItem(container, position, object);

        if (object instanceof Fragment) {
            final val fragment = (Fragment) object;

            onDestroyFragment(fragment, position);
        }
    }

    protected WeakFragmentPagerAdapter(@NonNull final FragmentManager fragmentManager) {
        super(Contracts.requireNonNull(fragmentManager, "fragmentManager == null"));
    }

    @CallSuper
    protected void onDestroyFragment(@NonNull final Fragment fragment, final int position) {
        Contracts.requireNonNull(fragment, "fragment == null");

        getFragments().remove(position);
    }

    @CallSuper
    protected void onInstantiateFragment(@NonNull final Fragment fragment, final int position) {
        Contracts.requireNonNull(fragment, "fragment == null");

        getFragments().append(position, new WeakReference<>(fragment));
    }

    @Getter(value = AccessLevel.PRIVATE)
    @NonNull
    private final SparseArray<Reference<Fragment>> _fragments = new SparseArray<>();

    @Getter
    @Setter
    @Nullable
    private PageFactory _pageFactory;
}
