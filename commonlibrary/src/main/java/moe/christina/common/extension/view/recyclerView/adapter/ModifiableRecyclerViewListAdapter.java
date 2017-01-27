package moe.christina.common.extension.view.recyclerView.adapter;

import android.support.annotation.NonNull;

import lombok.experimental.Accessors;
import lombok.val;

import moe.christina.common.contract.Contracts;
import moe.christina.common.extension.view.recyclerView.viewHolder.ExtendedRecyclerViewHolder;

import java.util.Collection;

@Accessors(prefix = "_")
public abstract class ModifiableRecyclerViewListAdapter<TItem, TViewHolder extends
    ExtendedRecyclerViewHolder>

    extends RecyclerViewListAdapter<TItem, TViewHolder> {
    public final void addItem(final int position, @NonNull final TItem newItem) {
        Contracts.requireNonNull(newItem, "newItem == null");

        addItem(position, newItem, true);
    }

    public final void addItem(
        final int position, @NonNull final TItem newItem, final boolean notify) {
        Contracts.requireInRange(position, 0, getItemCount(), new IndexOutOfBoundsException());
        Contracts.requireNonNull(newItem, "newItem == null");

        final val items = getItems();

        if (items != null) {
            items.add(position, newItem);

            if (notify) {
                notifyItemInserted(position);
            }
        }
    }

    public final void addItem(@NonNull final TItem newItem) {
        Contracts.requireNonNull(newItem, "newItem == null");

        addItem(newItem, true);
    }

    public final void addItem(@NonNull final TItem newItem, final boolean notify) {
        Contracts.requireNonNull(newItem, "newItem == null");

        addItem(getItemCount(), newItem, notify);
    }

    public final void addItems(@NonNull final Collection<TItem> newItems) {
        Contracts.requireNonNull(newItems, "newItems == null");

        addItems(newItems, true);
    }

    public final void addItems(@NonNull final Collection<TItem> newItems, final boolean notify) {
        Contracts.requireNonNull(newItems, "newItems == null");

        addItems(getItemCount(), newItems, notify);
    }

    public final void addItems(final int position, @NonNull final Collection<TItem> newItems) {
        Contracts.requireInRange(position, 0, getItemCount(), new IndexOutOfBoundsException());
        Contracts.requireNonNull(newItems, "newItems == null");

        addItems(position, newItems, true);
    }

    public final void addItems(
        final int location, @NonNull final Collection<TItem> newItems, final boolean notify) {
        Contracts.requireNonNull(newItems, "newItems == null");

        final val items = getItems();

        if (items != null) {
            items.addAll(location, newItems);

            if (notify) {
                notifyItemRangeInserted(location, newItems.size());
            }
        }
    }

    public final void removeItem(final int position) {
        Contracts.requireInRange(position, 0, getItemCount() - 1, new IndexOutOfBoundsException());

        removeItem(position, true);
    }

    public final void removeItem(final int position, final boolean notify) {
        Contracts.requireInRange(position, 0, getItemCount() - 1, new IndexOutOfBoundsException());

        final val items = getItems();

        if (items != null) {
            items.remove(position);

            if (notify) {
                notifyItemRemoved(position);
            }
        }
    }

    public final void removeItems(final boolean notify) {
        final val items = getItems();

        if (items != null) {
            final int itemsCount = items.size();

            items.clear();

            if (notify) {
                notifyItemRangeRemoved(0, itemsCount);
            }
        }
    }

    public final void removeItems() {
        removeItems(true);
    }

    public final void setItems(@NonNull final Collection<TItem> newItems) {
        Contracts.requireNonNull(newItems, "newItems == null");

        setItems(newItems, true);
    }

    public final void setItems(@NonNull final Collection<TItem> newItems, final boolean notify) {
        Contracts.requireNonNull(newItems, "newItems == null");

        final val items = getItems();

        if (items != null) {
            final int itemsCount = items.size();
            final int newItemsCount = newItems.size();

            final int changedCount = Math.min(itemsCount, newItemsCount);
            final int removedCount = Math.max(0, itemsCount - changedCount);
            final int insertedCount = Math.max(0, newItemsCount - changedCount);

            items.clear();
            items.addAll(newItems);

            if (notify) {
                if (changedCount > 0) {
                    notifyItemRangeChanged(0, changedCount);
                }
                if (removedCount > 0) {
                    notifyItemRangeRemoved(changedCount, removedCount);
                }
                if (insertedCount > 0) {
                    notifyItemRangeInserted(changedCount, insertedCount);
                }
            }
        }
    }
}
