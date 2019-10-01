package encryptdecrypt;

public enum TargetOperation {

    ENCRYPTION {
        @Override
        public int getShiftDirection() {
            return 1;
        }
    },
    DECRYPTION {
        @Override
        public int getShiftDirection() {
            return -1;
        }
    };

    public abstract int getShiftDirection();

}
