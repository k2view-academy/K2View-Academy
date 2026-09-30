# Long-Running Chats: Resume and Keep Awake

Some AI tasks run for many minutes. From V8.5.2, Studio AI keeps such chats from being lost when the page reloads, and can keep your screen awake while a chat is running.

## Resuming Interrupted Chats

A chat is **interrupted** when the assistant was still working on a response and the page went away - a browser reload (F5), a lost connection to the server, the laptop going to sleep, or a server restart.

After the Studio loads again:

- **The chat you were in reopens** in the AI Chat panel, instead of the chat overview.
- **Interrupted chats are listed** in an **Interrupted** section, both in the chat overview and in the AI Sessions view. Their rows carry an icon badge.
- **A card under the cut-off response** says that the response was interrupted and that steps the assistant was running may not have completed, with a **Continue** button. Continue is also offered on the chat's row in these lists.
- **One notification per startup** tells you that chats were interrupted - with **Open chat** when a single chat was interrupted, or **Review** when there were several.

Click **Continue** to let the assistant pick up where it stopped. It is sent as a message shown as "Continue", in the same mode as the last request (see [Ask, Plan and Act Modes](05_plan_first_development.md)). The assistant is told to re-check the current workspace state before repeating or completing any step, rather than assume earlier tool calls completed.

If you do not want to continue, run **Chat: Dismiss Interrupted Chats** from the command palette (**F1**) to clear the list.

Notes:

- The offer to continue is available only once. After you continue the chat, or send another message in it, the Continue buttons disappear.
- A chat that is open in another browser tab is not listed as interrupted in this tab.
- Continue sends a new request to the model, so it consumes tokens like any other message.

## Keeping the Screen Awake

If the screen locks or the laptop sleeps while a long task runs, the browser may drop the connection and interrupt the chat. To prevent that, turn on **Keep screen awake** for the chat. It applies to that chat only, and can be turned on in two places:

- **In the chat list** - on the AI Chat home screen, running chats are listed under **Active** at the top. Hover over a chat's row and click the **coffee cup** icon (next to the rename and delete icons). You can turn it on only for chats open in the current browser tab; you can turn it off for any chat.
- **Inside the chat** - with the chat open, choose **More Actions...** (**…**) in the AI Chat toolbar > **Keep screen awake while this chat is working**. Choose the same item again to turn it off.

You can also run **Keep Screen Awake for This Chat** from the command palette (**F1**).

While the setting is on and the chat is running, the screen stays awake, and a status bar item shows that the screen is being kept awake. Chats with the setting on show a badge in the chat list, and **Show Chats Keeping the Screen Awake** (command palette) lists them.

Keep screen awake is available in the browser Studio only. It requires the Studio to be served over HTTPS (or from `localhost`), and it works only while the Studio's browser tab is visible - switching to another tab or minimizing the window releases it.

## See Also

- [Using the AI Chat](03_using_the_ai_chat.md#chat-session-history) - chat session history
- [AI Features Settings](15_ai_features_settings.md#chat-behavior) - how many sessions are kept
