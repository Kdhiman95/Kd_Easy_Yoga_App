package com.example.easyyoga.utils

import com.example.easyyoga.R
import java.time.Duration

class ExercisesData {

	companion object{
		var totalDurationPerDay = 0L

		var dailyList = ArrayList<Exercises>()

		val beginnerPlanList = ArrayList<Exercises>()
		val intermediatePlanList = ArrayList<Exercises>()
		val advancedPlanList = ArrayList<Exercises>()

		fun updateListDuration(plan: String,position:Int,duration: Long){
			when(plan){
				"Beginner plan" -> beginnerPlanList[position].duration = duration
				"Intermediate plan" -> intermediatePlanList[position].duration = duration
				"Advanced plan" -> advancedPlanList[position].duration = duration
			}
		}

		fun addBeginnerPlanList() {
			beginnerPlanList.add(Exercises(1,
				"Seated Cat Cow",
				"Sit cross-legged and rest your hand on yur knees. As you inhale, lift your gaze and try to drive your chest out.\n\nOn an exhale, tuck your chin, draw your lower belly in and round your back. repeat the exercise.",
				50L,
				R.drawable.seat))

			beginnerPlanList.add(Exercises(2,
				"Crescent Low Lunge Left",
				"Start in a lunge position, but with your right leg on the floor. Tuck your pelvis in and sink your hips.\n\nRaise your arms up by your ears with palms facing each other. look Straight ahead. Hold this position.",
				50L,
				R.drawable.crescent_low_l))

			beginnerPlanList.add(Exercises(3,
				"Half Locust Pose",
				"Lie on your stomach with your legs extended, hans by your sides, and chin on the floor.\n\nInhale and lift your right leg as high as possible. Hold for a few seconds and switch to the other leg. Repeat the exercise.",
				50L,
				R.drawable.half_locust_pose))

			beginnerPlanList.add(Exercises(4,
				"Downward Facing Dog With Bent Knees",
				"Start on all fours with your knees under your buttocks and your hands directly under your shoulders.\n\nLift your sit bones up and back with your knees bent. Draw your lower abdomen in to support the spine.\n\nSpread your fingers and slightly spread your feet apart. Hold this position",
				50L,
				R.drawable.downward_facing_dog))

			beginnerPlanList.add(Exercises(5,
				"Crescent Low Lunge Right",
				"Start in a lunge position, but with your left leg on the floor. Tuck your pelvis in and sink your hips.\n\nRaise your arms up by your ears with palms facing each other. look Straight ahead. Hold this position.",
				50L,
				R.drawable.cresent_low_r))

			beginnerPlanList.add(Exercises(6,
				"Bird Dog",
				"Start with your knees under your butt and your hands under your shoulders.\n\nThen stretch your right leg and left arm at the same time.\n\nHold for five seconds, then go back and repeat with the other side.",
				50L,
				R.drawable.bird_dog))

			beginnerPlanList.add(Exercises(7,
				"Sphinx Pose",
				"Lie on your stomach with your forearms on the floor and your elbows under your shoulders. Extend your legs with the tops of your feet on the floor.\n\nInhale lift your upper body and extend your neck away from your shoulders. Hold this position.",
				50L,
				R.drawable.sphinx_pose))

			beginnerPlanList.add(Exercises(8,
				"Child's Pose",
				"Start with your knees and hands on the floor. Put your hands a little forward, widen your knees and put your toes together.\n\nTake a breath, then exhale and sit back. Try to make your butt touch your heels. Relax your elbows, make your forehead touch the floor and try to lower your chest close to the floor. Hold this position. \n\nKeep your arms stretched forward as you sit back. Make sure there is enough space between your shoulders and ears during the exercise.",
				50L,
				R.drawable.child_pose))

			beginnerPlanList.add(Exercises(9,
				"Corpse pose",
				"Lie on your back with your legs stretched. hands at your sides and palms up. Take deep relaxation on the floor.",
				50L,
				R.drawable.corpse_pose))

			beginnerPlanList.add(Exercises(10,
				"Arm Raises",
				"Stand on the floor with your arms extended straight forward at shoulder height.\n\nRaise your arms above your head. Return to the start position and repeat.",
				50L,
				R.drawable.arm_raises))

		}

		fun addIntermediatePlanList() {
			intermediatePlanList.add(Exercises(11,
				"Standing Knee To Chest",
				"Stand with your feet shoulder width apart, chest up and shoulders back. Lift your left knee up, and grab it with both hands.\n\nThen gently pull it towards your body as much as you can. Hold for a few seconds, switch sides and repeat.",
				50L,
				R.drawable.standing_k_t_c))

			intermediatePlanList.add(Exercises(12,
				"Goddess Pose",
				"Stand with your feet wide apart. Turn your heels in and toes out at 45 degrees.\n\nLower your body until your thighs are almost parallel to the floor. Draw your tailbone down and press your hips forward.\n\nLift your arms to your sides at shoulder height. Bend your arms at 90 degrees with your palms facing forward. Hold this position.",
				50L,
				R.drawable.goddess_pose))

			intermediatePlanList.add(Exercises(13,
				"Half Forward Bend",
				"Stand with your feet hip-width apart. Draw your belly in and tuck your tailbone down. Roll your shoulders back and down.\n" +
						"\n" +
						"Bend your upper body forward and rest your hands on your legs. Bend from your hips instead of your waist.\n" +
						"\n" +
						"Focus on keeping your back flat and lengthening your spine. Hold this position.",
				50L,
				R.drawable.half_forward_bend))

			intermediatePlanList.add(Exercises(14,
				"Crescent Low Lunge With Cactus Arms Left",
				"Start in a lunge position, but with your right leg on the floor. Tuck your pelvis in and sink your hips.\n" +
						"\n" +
						"Raise your arms up by your ears with your palms facing forward and fingers spread wide.\n" +
						"\n" +
						"Lift your chest up and back while bending your arms at 90 degrees.\n" +
						"\n" +
						"Your arms should be in line with your upper body. Keep your neck natural. Return and repeat.",
				50L,
				R.drawable.cresent_l_catus_l))

			intermediatePlanList.add(Exercises(15,
				"Warrior Li Left",
				"Stand with your feet wider than shoulder-width apart. Turn your left foot to the left and your right foot 60 degrees to the left..\n" +
						"\n" +
						"Lower your body until your left thigh is almost parallel to the floor. Raise your arms to your sides with your palms down.\n" +
						"\n" +
						"Draw your belly in, tuck your tailbone down, and lift your chest. Take your gaze to your left hand. Hold this position.",
				50L,
				R.drawable.warrior_li_left))

			intermediatePlanList.add(Exercises(16,
				"Cobras",
				"Lie down on your stomach and bend your elbows with your hands beneath your shoulders.\n" +
						"\n" +
						"Keep your toes back and your feet and legs relaxed on the ground.\n" +
						"\n" +
						"Then push your chest up off the ground as far as possible. Return to the start position and repeat.",
				50L,
				R.drawable.cobras))

			intermediatePlanList.add(Exercises(17,
				"Walk The Dog",
				"Start on all fours with your hips up in an upside-down 'V' shape. Alternately Lift and lower your heels.",
				50L,
				R.drawable.walk_the_dog))

			intermediatePlanList.add(Exercises(18,
				"Crescent Low lunge With Cactus Arms Right",
				"Start in a lunge position, but with your left leg on the floor. Tuck your pelvis in and sink your hips.\n" +
						"\n" +
						"Raise your arms up by your ears with your palms facing forward and fingers spread wide.\n" +
						"\n" +
						"Lift your chest up and back while bending your arms at 90 degrees.\n" +
						"\n" +
						"Your arms should be in line with your upper body. Keep your neck natural. Return and repeat.",
				50L,
				R.drawable.crescent_l_cactus_r))

			intermediatePlanList.add(Exercises(19,
				"Warrior Li Right",
				"Stand with your feet wider than shoulder-width apart. Turn your right foot to the right and your left foot 60 degrees to the right.\n" +
						"\n" +
						"Lower your body until your right thigh is almost parallel to the floor. Raise your arms to your sides with your palms down.\n" +
						"\n" +
						"Draw your belly in, tuck your tailbone down, and lift your chest. Take your gaze to your right hand. Hold this position.",
				50L,
				R.drawable.warrior_li_right))

			intermediatePlanList.add(Exercises(20,
				"Low Boat Pose",
				"Lie on your back with your legs extended and arms by your sides.\n" +
						"\n" +
						"Slightly lift your upper body and legs off the floor.\n" +
						"\n" +
						"Squeeze your inner thighs together. Extend your arms. Hold this position.",
				50L,
				R.drawable.low_boat_pose))

		}

		fun addAdvancedPlanList() {
			advancedPlanList.add(Exercises(21,
				"Palm Tree Pose",
				"Stand upright with your arms by your sides. Interlock your fingers and turn your palms upwards.\n" +
						"\n" +
						"Inhale, stretch your arms to the ceiling while simultaneously raising your heels. Your balance will shift to your toes.\n" +
						"\n" +
						"Hold for seconds. Return and repeat.",
				50L,
				R.drawable.palm_tree_pose))

			advancedPlanList.add(Exercises(22,
				"Forward Bend",
				"Stand on the floor with your feet together.\n" +
						"\n" +
						"Then bend your body and bring your upper body as close to your legs as you can.\n" +
						"\n" +
						"Hold this position for a few seconds and repeat.\n" +
						"\n" +
						"Exhale when you bend your body and inhale when you come up. Keep your legs straight during the exercise.",
				50L,
				R.drawable.forward_bend))

			advancedPlanList.add(Exercises(23,
				"Downward Facing Dog With Knee Drives Left",
				"Start on all fours with your hips up in an upside-down 'V' shape. Try to press your heels on the floor.\n" +
						"\n" +
						"Lift your left leg up to form a straight line with your back. Don't twist your hips.\n" +
						"\n" +
						"Move your body forward until your shoulders are directly above your hands, and bring your knee to your left elbow.\n" +
						"\n" +
						"Return and repeat the exercise.",
				50L,
				R.drawable.downward_f_d_k_d_l))

			advancedPlanList.add(Exercises(24,
				"Warrior L Left",
				"Stand with your feet hip-distance apart. Take your left foot forward while keeping your right leg straight.\n" +
						"\n" +
						"Turn your right foot 60 degrees to your right. Lower your body until your left thigh is almost parallel to the floor.\n" +
						"\n" +
						"Raise your arms toward the ceiling with your palms facing each other.\n" +
						"\n" +
						"Draw your belly in, tuck your tailbone down, and lift your chest. Hold this position.",
				50L,
				R.drawable.warrior_l_l))

			advancedPlanList.add(Exercises(25,
				"Warrior Li Left",
				"Stand with your feet wider than shoulder-width apart. Turn your left foot to the left and your right foot 60 degrees to the left..\n" +
						"\n" +
						"Lower your body until your left thigh is almost parallel to the floor. Raise your arms to your sides with your palms down.\n" +
						"\n" +
						"Draw your belly in, tuck your tailbone down, and lift your chest. Take your gaze to your left hand. Hold this position.",
				50L,
				R.drawable.warrior_li_left))

			advancedPlanList.add(Exercises(26,
				"Revolved Side Angle Left",
				"Stand upright. Take a big step forward with your left foot. Lower your body until your thigh is almost parallel to the floor.\n" +
						"\n" +
						"Twist your upper body to the left and extend your arms to the sides.\n" +
						"\n" +
						"Bring your hands together into a prayer position at the center of your chest.\n" +
						"\n" +
						"Press your right elbow against the outside of your left knee.\n" +
						"\n" +
						"Shift your gaze upward over your left shoulder. Hold this position.",
				50L,
				R.drawable.revolved_s_a_l))

			advancedPlanList.add(Exercises(27,
				"Cobras",
				"Lie down on your stomach and bend your elbows with your hands beneath your shoulders.\n" +
						"\n" +
						"Keep your toes back and your feet and legs relaxed on the ground.\n" +
						"\n" +
						"Then push your chest up off the ground as far as possible. Return to the start position and repeat.",
				50L,
				R.drawable.cobras))

			advancedPlanList.add(Exercises(28,
				"Downward Facing Dog With Knee Drives Right",
				"Start on all fours with your hips up in an upside-down 'V' shape. Try to press your heels on the floor.\n" +
						"\n" +
						"Lift your right leg up to form a straight line with your back. Don't twist your hips.\n" +
						"\n" +
						"Move your body forward until your shoulders are directly above your hands, and bring your knee to your right elbow.\n" +
						"\n" +
						"Return and repeat the exercise.",
				50L,
				R.drawable.downward_f_d_k_d_r))

			advancedPlanList.add(Exercises(29,
				"Warrior L Right",
				"Stand with your feet hip-distance apart. Take your right foot forward while keeping your left leg straight.\n" +
						"\n" +
						"Turn your left foot 60 degrees to your left. Lower your body until your right thigh is almost parallel to the floor.\n" +
						"\n" +
						"Raise your arms toward the ceiling with your palms\n" +
						"\n" +
						"facing each other. Draw your belly in, tuck your tailbone down, and lift your\n" +
						"\n" +
						"chest. Hold this position.",
				50L,
				R.drawable.warrior_l_r))

			advancedPlanList.add(Exercises(30,
				"Warrior Li Right",
				"Stand with your feet wider than shoulder-width apart. Turn your right foot to the right and your left foot 60 degrees to the right.\n" +
						"\n" +
						"Lower your body until your right thigh is almost parallel to the floor. Raise your arms to your sides with your palms down.\n" +
						"\n" +
						"Draw your belly in, tuck your tailbone down, and lift your chest. Take your gaze to your right hand. Hold this position.",
				50L,
				R.drawable.warrior_li_right))

		}
	}
}