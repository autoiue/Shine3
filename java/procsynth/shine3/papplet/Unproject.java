// Unproject.java

package procsynth.shine3.papplet;

import java.util.logging.Logger;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;
import processing.opengl.PGraphics3D;

/**
 * Helper class to determine screen to 3D world positions.
 *
 * @author Dr. Andrew Marsh - http://andrewmarsh.com
 * @since v0.0.1
 */
public class Unproject {

	private final Logger log = Logger.getLogger(this.getClass().getName());

	// True if near and far points calculated.
	private static boolean m_bValid = false;

	// Maintain own projection matrix.
	private static PMatrix3D m_pMatrix = new PMatrix3D();

	// Maintain own viewport data.
	private static int[] m_aiViewport = new int[4];

	// Store the near and far ray positions.
	private static PVector ptStartPos = new PVector();
	private static PVector ptEndPos = new PVector();

	/**
	 * This return the projected on screen point on a XY plane with a specific Z
	 * coordinates Call this many times as necessary after
	 * {@link #captureViewMatrix}.
	 *
	 * @param x
	 *            the x coordinate of the on screen point
	 * @param y
	 *            the y coordinate of the on screen point
	 * @param interZ
	 *            the z coordinate of a XY plane to intersect with
	 * @return the 3D vector of the projection of the point on the plane
	 * 
	 */
	public static PVector unproject(float x, float y, float interZ) {
		calculatePickPoints(x, y);
		return intersect(interZ);
	}

	/**
	 * This capture the view matrix of the PApplet and stores it. Call this first
	 * once you set the view with camera/ortho/rotate/translate/etc.
	 *
	 * @param p
	 *            PApplet state
	 * 
	 */
	public static void captureViewMatrix(PApplet p) { // Call this to capture the selection matrix after
														// you have called perspective() or ortho() and applied your
														// pan, zoom and camera angles - but before you start drawing
														// or playing with the matrices any further.

		PGraphics3D g3d = (PGraphics3D) p.g;

		// if (g3d == null)
		// { // Use main canvas if it is P3D, OPENGL or A3D.
		// g3d = (PGraphics3D)g;
		// }

		if (g3d != null) { // Check for a valid 3D canvas.

			// Capture current projection matrix.
			m_pMatrix.set(g3d.projection);

			// Multiply by current modelview matrix.
			m_pMatrix.apply(g3d.modelview);

			// Invert the resultant matrix.
			m_pMatrix.invert();

			// Store the viewport.
			m_aiViewport[0] = 0;
			m_aiViewport[1] = 0;
			m_aiViewport[2] = g3d.width;
			m_aiViewport[3] = g3d.height;

		}

	}

	/**
	 * Take a point on screen and project it on a plane (winz) paralle to the
	 * screen.
	 *
	 * @param winx
	 *            The coordinates x of the point a on screen
	 * @param winy
	 *            The coordinates y of the point a on screen
	 * @param winz
	 *            The distance where the projected point should be put
	 * @param result
	 *            the PVector to put in the result
	 *
	 * @return If the given point is valid.
	 * 
	 */
	private static boolean gluUnProject(float winx, float winy, float winz, PVector result) {

		float[] in = new float[4];
		float[] out = new float[4];

		// Transform to normalized screen coordinates (-1 to 1).
		in[0] = ((winx - (float) m_aiViewport[0]) / (float) m_aiViewport[2]) * 2.0f - 1.0f;
		in[1] = ((winy - (float) m_aiViewport[1]) / (float) m_aiViewport[3]) * 2.0f - 1.0f;
		in[2] = PApplet.constrain(winz, 0f, 1f) * 2.0f - 1.0f;
		in[3] = 1.0f;

		// Calculate homogeneous coordinates.
		out[0] = m_pMatrix.m00 * in[0] + m_pMatrix.m01 * in[1] + m_pMatrix.m02 * in[2] + m_pMatrix.m03 * in[3];
		out[1] = m_pMatrix.m10 * in[0] + m_pMatrix.m11 * in[1] + m_pMatrix.m12 * in[2] + m_pMatrix.m13 * in[3];
		out[2] = m_pMatrix.m20 * in[0] + m_pMatrix.m21 * in[1] + m_pMatrix.m22 * in[2] + m_pMatrix.m23 * in[3];
		out[3] = m_pMatrix.m30 * in[0] + m_pMatrix.m31 * in[1] + m_pMatrix.m32 * in[2] + m_pMatrix.m33 * in[3];

		if (out[3] == 0.0f) { // Check for an invalid result.
			result.x = 0.0f;
			result.y = 0.0f;
			result.z = 0.0f;
			return false;
		}

		// Scale to world coordinates.
		out[3] = 1.0f / out[3];
		result.x = out[0] * out[3];
		result.y = out[1] * out[3];
		result.z = out[2] * out[3];
		return true;

	}

	/**
	 * Take a point on screen and project it the near and far plane of the fustrum.
	 *
	 * @param x
	 *            The coordinates X of the point on screen.
	 * @param y
	 *            The coordinates Y of the point on screen.
	 *
	 * @return If the given point is valid.
	 * 
	 */
	private static boolean calculatePickPoints(float x, float y) { // Calculate positions on the near and far 3D frustum
																	// planes.
		m_bValid = true; // Have to do both in order to reset PVector on error.

		// m_aiViewport[3] == p.height
		if (!gluUnProject((float) x, m_aiViewport[3] - (float) y, 0.0f, ptStartPos))
			m_bValid = false;
		if (!gluUnProject((float) x, m_aiViewport[3] - (float) y, 1.0f, ptEndPos))
			m_bValid = false;
		return m_bValid;
	}

	/**
	 * Calculate the intersection point between a plane parallel to the XY plane and
	 * the unprojected points.
	 *
	 * @param z
	 *            The distance between XY plane and the plane to intersect to.
	 *
	 * @return The 3D intersection point
	 * 
	 */

	private static PVector intersect(float z) {
		//
		// ptStartPos , ptEndPos: define the line
		// p_co, p_no: define the plane:
		// p_co is a point on the plane (plane coordinate).
		// p_no is a normal vector defining the plane direction;
		// (does not need to be normalized).

		// return a Vector or None (when the intersection can't be found).
		//
		PVector p_co = new PVector(0, 0, z);
		PVector p_no = new PVector(0, 0, 1);
		PVector u = PVector.sub(ptStartPos, ptEndPos);
		float dot = PVector.dot(p_no, u);

		// the factor of the point between p0 -> p1 (0 - 1)
		// if 'fac' is between (0 - 1) the point intersects with the segment.
		// otherwise:
		// < 0.0: behind p0.
		// > 1.0: infront of p1.

		PVector w = PVector.sub(ptStartPos, p_co);
		float fac = -PVector.dot(p_no, w) / dot;
		u = PVector.mult(u, fac);
		return PVector.add(ptStartPos, u);
	}

}