package quetzalsoft.berserkr.qm;

/**
 * Created by lbathen on 7/25/17.
 */

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;

import java.security.SecureRandom;

public class QMKeyManager {
    DeterministicHierarchy deterministicHierarchy;
    DeterministicKey rootKey;
    ECKey privateKey;
    NetworkParameters parameters;

    public QMKeyManager() {
    }

    public boolean generateKeys() {
        privateKey = new ECKey(new SecureRandom());
//        rootKey = (DeterministicKey) privateKey;
//        deterministicHierarchy = new DeterministicHierarchy(rootKey);
        parameters = NetworkParameters.fromID(NetworkParameters.ID_REGTEST);
        return true;
    }

    public void setDeterministicHierarchy(DeterministicHierarchy deterministicHierarchy) {
        this.deterministicHierarchy = deterministicHierarchy;
    }

    public void setPrivateKey(ECKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setRootKey(DeterministicKey rootKey) {
        this.rootKey = rootKey;
    }

    public String getPublicKeyAsHex(ECKey key) {
        return key.getPublicKeyAsHex();
    }

    public String getBase58Address(NetworkParameters parameters, byte [] addressAsBytes) {
        if(addressAsBytes!=null) {
            return new Address(parameters, addressAsBytes).toBase58();
        }
        return null;
    }

    public DeterministicHierarchy getDeterministicHierarchy() {
        return deterministicHierarchy;
    }

    public DeterministicKey getRootKey() {
        return rootKey;
    }

    public ECKey getPrivateKey() {
        return privateKey;
    }

    // hold data for later usex
    private static final QMKeyManager manager = new QMKeyManager();
    public static QMKeyManager getInstance() {return manager;}

}
