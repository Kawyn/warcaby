package checkers.Universal;

public enum PlayerColor implements IPlayerColor {
    WHITE {
        @Override
        public String toHex() {
            return "FFFFFF";
        }
    }, BLACK {
        @Override
        public String toHex() {
            return "000000";
        }
    },
}

