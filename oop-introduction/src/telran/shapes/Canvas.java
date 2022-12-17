package telran.shapes;

import java.util.Arrays;

public class Canvas extends Shape {
	private Shape[] shapes;
	private Directions direction = Directions.ROW;
	private static Directions directionOrig;
	private int margin = 5;

	private static int[] snapshot;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	@Override
	public String[] presentation(int offset) {
		String[] res = new String[0];
		directionOrig = direction;
		storeNestesDirections(shapes); // store snapshots of all nested directions

		setNestedDirection(shapes); // changes all nested directions to the parent's one

		if (direction == Directions.COLUMN) {
				res = colPresentation(res,  offset);
		} else {
			res = rowPresentation(res, offset);
		}

		restoreNestedDirections(); // restore nested directions

		return res;
	}

	private String[] rowPresentation(String[] source, int offset) {
		String[] res = new String[getHeight()];
		Arrays.fill(res, getOffset(offset));
		for (int j=0; j<shapes.length-1;j++) {
			shapes[j].setHeight(getHeight());
			String[] shapePresentation = shapes[j].presentation(0);
			for (int i = 0; i < shapePresentation.length; i++) {
				res[i] += shapePresentation[i] + getOffset(margin);
			}
		}
		// right border condition: without margin
		shapes[shapes.length-1].setHeight(getHeight());
		String[] shapePresentation = shapes[shapes.length-1].presentation(0);
		for (int i = 0; i < shapePresentation.length; i++) {
			res[i] += shapePresentation[i] ;
		}
		return res;
	}

	private String[] colPresentation(String[] source, int offset) {
		String[] res = new String[0];
		for (var i = 0; i<shapes.length-1; i++) {
			res = colPresentationPerShape(res, shapes[i], offset);
		}
		// right border condition: without margin
		int margintemp = margin;
		margin = 0;
		res = colPresentationPerShape(res, shapes[shapes.length-1], offset);
		margin = margintemp;
		return res;
	}
	private String[] colPresentationPerShape(String[] source, Shape shape, int offset) {
		shape.setWidth(this.getWidth()); // this could be commented if there wasn't condition
											// all shapes have the same width in vertical representation

		var present = shape.presentation(offset);
		String[] res = Arrays.copyOf(source, source.length + present.length + margin);
		System.arraycopy(shape.presentation(offset), 0, res, source.length, present.length);

		// add margin - margin here is a count of empty strings between shapes
		String[] marginArray = new String[margin];
		Arrays.fill(marginArray, "");
		System.arraycopy(marginArray, 0, res, source.length + present.length, margin);
		return res;
	}

	/**
	 * make a snapshot of all directions of nested canvas
	 */
	private void storeNestesDirections(Shape[] shapes) {
		for (var shape : shapes) {
			if (shape instanceof Canvas) {
				Directions curDirection = ((Canvas) shape).direction;
				if (curDirection != directionOrig) {
					addToSnapshot(shape.hashCode());
				}
				((Canvas) shape).storeNestesDirections(((Canvas) shape).shapes);
			}
		}
	}

	private void addToSnapshot(int number) {
		int newlength = 1;
		if (snapshot == null) {
			snapshot = new int[] { 0 };
		} else {
			newlength = snapshot.length + 1;
		}
		snapshot = Arrays.copyOf(snapshot, newlength);
		snapshot[newlength - 1] = number;
	}

	/**
	 * restore directions
	 */
	private void restoreNestedDirections() {
		if (snapshot !=null)
		for (int i = 0; i < snapshot.length; i++) {
			findDirections(shapes, snapshot[i]);
		}
	}

	private void findDirections(Shape[] shapes, int hashcode) {
		for (Shape shape : shapes) {
			if (shape instanceof Canvas) {
				if (shape.hashCode() == hashcode) {
					((Canvas) shape).direction = directionOrig == Directions.COLUMN ? Directions.ROW
							: Directions.COLUMN;
					break;
				}
				findDirections(((Canvas) shape).shapes, hashcode);
			}
		}
	}
	/**
	 * changes direction for all successors to the direction of the parent
	 * @param shapes
	 */
	private void setNestedDirection(Shape[] shapes) {
		for (var shape : shapes) {
			if (shape instanceof Canvas) {
				((Canvas) shape).setDirection(direction); // changing direction for successor
				((Canvas) shape).setNestedDirection(((Canvas) shape).shapes); // processing for all sub-canvases

			}
		}
	}

	public void setDirection(Directions direction) {
		this.direction = direction;

	}

	public void setMargin(int margin) {
		this.margin = margin;
	}
}
