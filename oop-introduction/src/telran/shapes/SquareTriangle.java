
package telran.shapes;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	public SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String[] presentation(int offset) {
		String res[] = new String[super.getWidth()];
		String line = super.getLine(offset);
		res[0] = isLeftDiagonal ? super.getOffset(offset) + getSymbol()
				: super.getOffset(offset + super.getWidth() - 1) + getSymbol();
		int lastLine = super.getWidth() - 1;
		res[lastLine] = line;
		for (int i = 1; i < lastLine; i++) {
			res[i] = getMidlleLine(offset, i);
		}
		return res;
	}

	protected String getMidlleLine(int offset, int i) {
		String symbol = getSymbol();
		String res;
		if (isLeftDiagonal) {
			res = getOffset(offset) + symbol + getOffset(i - 1) + symbol;
		} else {
			res = getOffset(offset + super.getWidth() - i - 1) + symbol + getOffset(i - 1) + symbol;
		}

		return res;
	}
}
