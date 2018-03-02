'use strict';

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);


exports.sendNotification = functions.database.ref('/notificationToSend').onWrite((event) => {
  

  const data=event.data.val();
  
  if (!event.data.val()) {
    return console.log('no notifications');
  }
  console.log(event.data.val());

  // Get the list of device notification tokens.
  const getDeviceTokensPromise = admin.database().ref('/notificationTokens').once('value');

  

  return Promise.all([getDeviceTokensPromise]).then((results) => {
    const tokensSnapshot = results[0];
    

    // Check if there are any device tokens.
    if (!tokensSnapshot.hasChildren()) {
      return console.log('There are no notification tokens to send to.');
    }
    console.log('There are', tokensSnapshot.numChildren(), 'tokens to send notifications to.');
    

    // Notification details.
    const payload = {
      notification: {
        title:data.title,
        body:data.body,
        sound:'default',
      },
      data:{
        title:data.title,
        body:data.body,
      },
    };

    // Listing all tokens.
    const tokens = Object.keys(tokensSnapshot.val());

    // Send notifications to all tokens.
    return admin.messaging().sendToDevice(tokens, payload);
  }).then((response) => {
    // For each message check if there was an error.

    const tokensToRemove = [];
    response.results.forEach((result, index) => {
      const error = result.error;
      if (error) {
        console.error('Failure sending notification to', error);
        // Cleanup the tokens who are not registered anymore.
        if (error.code === 'messaging/invalid-registration-token' || error.code === 'messaging/registration-token-not-registered') {
          //tokensToRemove.push(tokensSnapshot.ref.child(tokens[index]).remove());
        }
      }
      
    });
    return Promise.all(tokensToRemove);
  });
});