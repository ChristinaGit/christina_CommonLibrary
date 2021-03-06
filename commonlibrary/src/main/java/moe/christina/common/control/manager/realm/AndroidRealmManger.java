package moe.christina.common.control.manager.realm;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;

import moe.christina.common.contract.Contracts;
import moe.christina.common.control.adviser.ResourceAdviser;
import moe.christina.common.control.manager.ReleasableManager;
import moe.christina.common.data.realm.RealmIdGenerator;
import moe.christina.common.utility.ResourceUtils;

@Accessors(prefix = "_")
public class AndroidRealmManger extends ReleasableManager implements RealmManager {
    public AndroidRealmManger(
        @NonNull final ResourceAdviser resourceAdviser,
        @NonNull final RealmConfiguration realmConfiguration,
        @NonNull final RealmIdGenerator realmIdGenerator) {
        super(Contracts.requireNonNull(resourceAdviser, "resourceAdviser == null"));
        Contracts.requireNonNull(realmConfiguration, "realmConfiguration == null");
        Contracts.requireNonNull(realmIdGenerator, "realmIdGenerator == null");

        _realmConfiguration = realmConfiguration;
        _realmIdGenerator = realmIdGenerator;
    }

    @Override
    public long generateNextId(@NonNull final Class<? extends RealmModel> modelClass) {
        Contracts.requireNonNull(modelClass, "modelClass == null");

        return getRealmIdGenerator().generateNextId(modelClass);
    }

    @NonNull
    @Override
    public final Realm getRealm() {
        if (_realm == null) {
            throw new IllegalStateException("Realm is not created or already released.");
        }

        return _realm;
    }

    protected void createRealm() {
        _realm = Realm.getInstance(getRealmConfiguration());
    }

    protected void destroyRealm() {
        if (_realm != null) {
            ResourceUtils.quietClose(_realm);
            _realm = null;
        }
    }

    @CallSuper
    @Override
    protected void onAcquireResources() {
        createRealm();
    }

    @CallSuper
    @Override
    protected void onReleaseResources() {
        destroyRealm();
    }

    @Getter(onMethod = @__(@Override))
    @NonNull
    private final RealmConfiguration _realmConfiguration;

    @Getter(AccessLevel.PROTECTED)
    private final RealmIdGenerator _realmIdGenerator;

    @Nullable
    private Realm _realm;
}
