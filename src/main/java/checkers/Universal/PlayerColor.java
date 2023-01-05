package checkers.Universal;

public enum PlayerColor implements IPlayerColor {
    WHITE {
        @Override
        public String getColorAsHex() {
            return "FFFFFF";
        }
    },
    BLACK {
        @Override
        public String getColorAsHex() {
            return "000000";
        }
    },
}

