# Chat Playground Branding and Customization

The AI Fusion chat application supports customization through CSS variables, texts, and images. You can define several branding and customization sets, per app (CRM, Technicians, end-customers). 

## Configuration Files Location

```
Implementation/LogicalUnits/aifusion/web/chat/
├── styles/
│   ├── style.css						# Base styles (do not modify)
│   ├── customize-style.css				# Default customization styling (Use it as a reference)
│   ├── myappid-customize-style.css		# your app customized styling definitions
├── img/
│   ├── logo.png                       	# Default logo
│   ├── myappid-logo.png				# your app logo
│   ├── myappid-mini-logo.png			# your app badge
└── translations.json                  	# UI text phrases 
```



## Create Custom CSS File

Create a file named `styles/{aifusionAppId}-customize-style.css`. 

The app will automatically load this file based on the configured `aifusionAppId`. If not found, it falls back to `customize-style.css`.

>  The *aifusionAppId* is the one you defined in the apps.csv and referred to at the apps.json. Read [here](12_project_impl_prep.md) for more setup information.

### CSS Variables Reference

| Variable                   | Description              | Example Values                             |
| -------------------------- | ------------------------ | ------------------------------------------ |
| **Branding**               |                          |                                            |
| `--main-bg-color`          | Primary theme color      | `#3b82f6`                                  |
| `--main-logo`              | Header logo image        | `url("../img/banking-logo.png")`           |
| `--assistant-badge`        | Assistant message icon   | `url('../chat/img/banking-mini-logo.png')` |
| `--user-badge`             | User message icon        | SVG data URL or image path                 |
| `--ai-badge`               | AI badge icon            | `url(../img/mycompany-logo.png)`           |
| `--llm-badge`              | LLM indicator icon       | SVG data URL                               |
| `--tool-badge`             | Tool execution icon      | SVG data URL                               |
| **Top Bar Buttons**        |                          |                                            |
| `--button-bg`              | Button background        | `white`                                    |
| `--button-border-color`    | Button border            | `#3b82f6`                                  |
| `--button-hover-bg`        | Button hover state       | `#1976d20a`                                |
| `--button-active-bg`       | Active button background | `#3b82f6`                                  |
| `--button-active-hover-bg` | Active button hover      | `#1274d6`                                  |
| **Side Boxes**             |                          |                                            |
| `--sidebox-title-color`    | Side panel title color   | `#3b82f6`                                  |
| **Trace Panel**            |                          |                                            |
| `--trace-resizer-hover-bg` | Resizer hover color      | `#b2d1ef`                                  |
| `--trace-panel-bg`         | Trace panel background   | `#f2f2f2`                                  |
| `--trace-panel-header-bg`  | Trace header background  | `rgb(219 234 254 / 1)`                     |

### Display Toggle Variables

| Variable                                | Values                  | Description                                                  |
| --------------------------------------- | ----------------------- | ------------------------------------------------------------ |
| `--user-search-display`                 | `flex` / `none`         | Show search popup for LLU IID (`flex`) or LUI list (`none`). <br />**Note**: The alterative for the search LUI popup is a popup window, divided to boxes, where each show short LUI synopsis. This option is useful for demo or for development/QA stages, where you have ongoing LUIs for testing. |
| `--user-profile-button-display`         | `inline-block` / `none` | Show/hide User Profile button at the top bar                 |
| `--chat-summary-button-display`         | `inline-block` / `none` | Show/hide Chat Summary button at the top bar                 |
| `--user-profile-sidebox-display-onload` | `flex` / `none`         | Auto-show profile panel on page loading                      |
| `--chat-summary-sidebox-display-onload` | `flex` / `none`         | Auto-show summary panel on page loading                      |
| `--trace-button-display`                | `block` / `none`        | Show/hide Trace button                                       |
| `--trace-info-header-display`           | `block` / `none`        | Show/hide trace info header                                  |

### RTL (Right-to-Left) Support

| Variable                    | LTR Value      | RTL Value     |
| --------------------------- | -------------- | ------------- |
| `--sidebox-title-direction` | `row`          | `row-reverse` |
| `--sidebox-text-direction`  | `start`        | `end`         |
| `--trace-direction`         | `auto` (right) | `0` (left)    |
| `--sideboxes-direction`     | `row`          | `row-reverse` |



## Add Images

Place your logo and badge images in the `img/` folder:

- **Main logo**: `{aifusionAppId}-logo.png` - Displayed in the header
- **Mini logo**: `{aifusionAppId}-mini-logo.png` - Used as assistant badge in chat messages

Reference them in your CSS:

```css
--main-logo: url("../img/myappid-logo.png");
--assistant-badge: url('../chat/img/myappid-mini-logo.png');
```



## Configure Texts

Add entries to `translations.json` with your `aifusionAppId` prefix (e.g. *myappid*):

```json
{
    "myappid-summary_button": "Chat Summary",
    "myappid-user_profile_button": "User Profile",
    "myappid-restart_button": "Restart",
    "myappid-main_title": "How can we help you today",
    "myappid-user_profile_box_title": "User Profile",
    "myappid-summary_box_title": "Chat Summary",
    "myappid-assistant_title": "Virtual Assistant",
    "myappid-search_popup_title": "Users Search",
    "myappid-search_popup_error": "User does not exist.",
    "myappid-search_button": "Search",
    "myappid-trace_button": "Trace",
    "myappid-chat_input_placeholder": "Ask me anything...",
    "myappid-chat_initial_message": "Hello! How can I assist you today?"
}
```
