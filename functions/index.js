const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

exports.resetValuesAtMidnight = functions.pubsub
  .schedule("every day 00:00")
  .timeZone("Europe/Warsaw")
  .onRun(async (context) => {
    const usersRef = admin.database().ref("users");

    try {
      const snapshot = await usersRef.once("value");

      snapshot.forEach((userSnapshot) => {
        const userRef = userSnapshot.ref;
        userRef.update({
          calories_eaten: 0,
          carbs_eaten: 0,
          fat_eaten: 0,
          proteins_eaten: 0,
          water_intake: 0,
          caloriesBurned: 0,
        });
      });

      console.log("Reset values for all users at midnight.");
      return null;
    } catch (error) {
      console.error("Error resetting values:", error);
      return null;
    }
  });
