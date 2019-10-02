package encryptdecrypt;

public enum Algorithm {

    SHIFT {
        @Override
        public char operate(char c, int key, TargetOperation targetOperation) {
            if (c >= 'a' && c <= 'z') {
                return (char) shift(c, key, targetOperation, 'a');
            } else if (c >= 'A' && c <= 'Z') {
                return (char) shift(c, key, targetOperation, 'A');
            } else {
                return c;
            }
        }

        private int shift(char c, int key, TargetOperation targetOperation, char baseLetter) {
            return (baseLetter + (c + targetOperation.getShiftDirection() * key - baseLetter + 26) % 26);
        }
    },
    UNICODE {
        @Override
        public char operate(char c, int key, TargetOperation targetOperation) {
            return (char) (c + targetOperation.getShiftDirection() * key);
        }
    };

    abstract public char operate(char c, int key, TargetOperation targetOperation);
}
