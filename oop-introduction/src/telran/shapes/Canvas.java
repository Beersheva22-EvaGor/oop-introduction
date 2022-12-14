package telran.shapes;

import java.util.Arrays;

public class Canvas extends Shape {
	private Shape[] shapes;
	private Directions direction = Directions.ROW;
	private int margin = 5;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	@Override
	public String[] presentation(int offset) {
		String[] res = new String[0];

		if (direction == Directions.COLUMN) {
			for (Shape shape : shapes) {
				res = colPresentation(res, shape, offset);
			}
		} else {
			res = rowPresentation(res, offset);
		}

		return res;
	}

	private String[] rowPresentation(String[] source, int offset) {
		String[] res = new String[getHeight()];
		Arrays.fill(res, getOffset(offset));
		for (Shape shape: shapes) {
			shape.setHeight(getHeight());
			String[] shapePresentation = shape.presentation(0);
			for (int i = 0; i < shapePresentation.length; i++) {
				res[i] += shapePresentation[i] + getOffset(margin);
			}
			
		}
		
		return res;
	}

	private String[] colPresentation(String[] source, Shape shape, int offset) {
		shape.setWidth(this.getWidth()); // this could be commented if there wasn't condition
											// all shapes have the same width in vertical representation
		String[] res = Arrays.copyOf(source, source.length + shape.getHeight() + margin);
		System.arraycopy(shape.presentation(offset), 0, res, source.length, shape.presentation(offset).length);

		// add margin - margin here is a count of empty strings between shapes
		String[] marginArray = new String[margin];
		Arrays.fill(marginArray, "");
		System.arraycopy(marginArray, 0, res, source.length + shape.getHeight(), margin);
		return res;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}
}
