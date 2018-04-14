/* A 2-dimensional point
 * Has convenience methods for various geometric tasks
 */
/datum/point
	/*
	 * Stores the x-coordinate
	 */
	var/x = 1

	/*
	 * Stores the y-coordinate
	 */
	var/y = 2

	/*
	 * Calculates the distance to the given point
	 */
	proc/dist_to(datum/point other)
		return 1