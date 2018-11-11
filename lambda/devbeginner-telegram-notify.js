/**
 * Created by jojoldu@gmail.com on 2018. 11. 9.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */


const TOKEN = process.env.TOKEN;
const TABLE_NAME = process.env.TABLE_NAME;
const MY_CHAT_ID = process.env.MY_CHAT_ID;

const https = require('https');
const util = require('util');
const AWS = require('aws-sdk'),
    docClient = new AWS.DynamoDB.DocumentClient();

exports.handler = async (event) => {
    console.log(JSON.stringify(event));
    const requestChatId = event.message["chat_id"];
    const requestText = event.message.text;
    let totalItem = 1;

    try {
        if (requestChatId) {
            const postData = {
                "chat_id": requestChatId,
                "text": createText(requestText)
            };
            await sendMessage(postData);
        } else {
            const response = await getChatIdAll();
            totalItem = response.Items;
            await sendAll(requestText, response.Items);
        }
    } catch (err) {
        console.log(err);
        return err;
    }

    return {totalCount: totalItem.length};
};

function getChatIdAll() {
    return docClient.scan({TableName: TABLE_NAME}).promise();
}

async function sendAll(requestText, items) {
    console.log("총 발송인원: " + items.length);
    console.log(JSON.stringify(items));

    items.forEach(async subscriber => {
        const postData = {
            //"chat_id": subscriber['chat_id'],
            "chat_id": MY_CHAT_ID,
            "text": createText(requestText)
        };

        await sendMessage(postData);
    });
}

function createText(requestText) {
    const suffixMessage = "\n\n전체 채용 & 행사 일정을 원하시면 /recruits 을 클릭해보세요";
    return requestText + suffixMessage;
}

function sendMessage(postData) {
    return new Promise((resolve, reject) => {
        const options = {
            method: 'POST',
            hostname: 'api.telegram.org',
            port: 443,
            headers: {"Content-Type": "application/json"},
            path: "/bot" + TOKEN + "/sendMessage"
        };

        const req = https.request(options, (res) => {
            res.setEncoding('utf8');
            res.on('data', (chunk) => {
                console.log('[전송성공] ' + postData['chat_id']);
            });
            resolve('Success');
        });

        req.on('error', function (e) {
            console.log('problem with request: ' + e.message);
            reject(e.message);
        });

        console.log('전송 chatId=', postData['chat_id']);
        req.write(util.format("%j", postData));
        req.end();
    });
}

