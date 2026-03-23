# Chat Playground Branding and Customization

The AI Fusion chat application supports customization through CSS variables, texts, and images. You can define several branding and customization sets, per app (CRM, Technicians, end-customers). 

## Configuration Files Location

```
Implementation/LogicalUnits/aifusion/web/chat/
├── styles/
│   ├── style.css# Base styles (do not modify)
│   ├── customize-style.css# Default customization styling (Use it as a reference)
│   ├── myappid-customize-style.css# your app customized styling definitions
├── img/
│   ├── logo.png                       # Default logo
│   ├── myappid-logo.png# your app logo
│   ├── myappid-mini-logo.png# your app badge
└── translations.json                  # UI text phrases 
```



## Create Custom CSS File

Create a file named `styles/{aifusionAppId}-customize-style.css`. 

The app will automatically load this file based on the configured `aifusionAppId`. If not found, it falls back to `customize-style.css`.

>  The *aifusionAppId* is the one you defined in the apps.csv and referred to at the apps.json. Read [here](12_project_impl_prep.md) for more setup information.

### CSS Variables Reference

<table>
<tbody>
<tr>
<td><strong>Variable</strong></td>
<td><strong>Description</strong></td>
<td><strong>Example Values</strong></td>
</tr>
<tr>
<td colspan="3"><strong>Branding</strong></td>
</tr>
<tr>
<td><code>--main-bg-color</code></td>
<td>Primary theme color</td>
<td><code>#3b82f6</code></td>
</tr>
<tr>
<td><code>--main-logo</code></td>
<td>Header logo image</td>
<td><code>url("../img/banking-logo.png")</code></td>
</tr>
<tr>
<td><code>--assistant-badge</code></td>
<td>Assistant message icon</td>
<td><code>url('../chat/img/banking-mini-logo.png')</code></td>
</tr>
<tr>
<td><code>--user-badge</code></td>
<td>User message icon</td>
<td>SVG data URL or image path</td>
</tr>
<tr>
<td><code>--ai-badge</code></td>
<td>AI badge icon</td>
<td><code>url(../img/mycompany-logo.png)</code></td>
</tr>
<tr>
<td><code>--llm-badge</code></td>
<td>LLM indicator icon</td>
<td>SVG data URL</td>
</tr>
<tr>
<td><code>--tool-badge</code></td>
<td>Tool execution icon</td>
<td>SVG data URL</td>
</tr>
<tr>
<td colspan="3"><strong>Top Bar Buttons</strong></td>
</tr>
<tr>
<td><code>--button-bg</code></td>
<td>Button background</td>
<td><code>white</code></td>
</tr>
<tr>
<td><code>--button-border-color</code></td>
<td>Button border</td>
<td><code>#3b82f6</code></td>
</tr>
<tr>
<td><code>--button-hover-bg</code></td>
<td>Button hover state</td>
<td><code>#1976d20a</code></td>
</tr>
<tr>
<td><code>--button-active-bg</code></td>
<td>Active button background</td>
<td><code>#3b82f6</code></td>
</tr>
<tr>
<td><code>--button-active-hover-bg</code></td>
<td>Active button hover</td>
<td><code>#1274d6</code></td>
</tr>
<tr>
<td colspan="3"><strong>Side Boxes</strong></td>
</tr>
<tr>
<td><code>--sidebox-title-color</code></td>
<td>Side panel title color</td>
<td><code>#3b82f6</code></td>
</tr>
<tr>
<td colspan="3"><strong>Trace Panel</strong></td>
</tr>
<tr>
<td><code>--trace-resizer-hover-bg</code></td>
<td>Resizer hover color</td>
<td><code>#b2d1ef</code></td>
</tr>
<tr>
<td><code>--trace-panel-bg</code></td>
<td>Trace panel background</td>
<td><code>#f2f2f2</code></td>
</tr>
<tr>
<td><code>--trace-panel-header-bg</code></td>
<td>Trace header background</td>
<td><code>rgb(219 234 254 / 1)</code></td>
</tr>
</tbody>
</table>

### Display Toggle Variables

<table>
<tbody>
<tr>
<td><strong>Variable</strong></td>
<td><strong>Values</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>--user-search-display</code></td>
<td><code>flex</code> / <code>none</code></td>
<td>Show search popup for LLU IID (<code>flex</code>) or LUI list (<code>none</code>). <br/><strong>Note</strong>: The alternative for the search LUI popup is a popup window, divided to boxes, where each show short LUI synopsis. This option is useful for demo or for development/QA stages, where you have ongoing LUIs for testing.</td>
</tr>
<tr>
<td><code>--user-profile-button-display</code></td>
<td><code>inline-block</code> / <code>none</code></td>
<td>Show/hide User Profile button at the top bar</td>
</tr>
<tr>
<td><code>--chat-summary-button-display</code></td>
<td><code>inline-block</code> / <code>none</code></td>
<td>Show/hide Chat Summary button at the top bar</td>
</tr>
<tr>
<td><code>--user-profile-sidebox-display-onload</code></td>
<td><code>flex</code> / <code>none</code></td>
<td>Auto-show profile panel on page loading</td>
</tr>
<tr>
<td><code>--chat-summary-sidebox-display-onload</code></td>
<td><code>flex</code> / <code>none</code></td>
<td>Auto-show summary panel on page loading</td>
</tr>
<tr>
<td><code>--trace-button-display</code></td>
<td><code>block</code> / <code>none</code></td>
<td>Show/hide Trace button</td>
</tr>
<tr>
<td><code>--trace-info-header-display</code></td>
<td><code>block</code> / <code>none</code></td>
<td>Show/hide trace info header</td>
</tr>
</tbody>
</table>

### RTL (Right-to-Left) Support

<table>
<tbody>
<tr>
<td><strong>Variable</strong></td>
<td><strong>LTR Value</strong></td>
<td><strong>RTL Value</strong></td>
</tr>
<tr>
<td><code>--sidebox-title-direction</code></td>
<td><code>row</code></td>
<td><code>row-reverse</code></td>
</tr>
<tr>
<td><code>--sidebox-text-direction</code></td>
<td><code>start</code></td>
<td><code>end</code></td>
</tr>
<tr>
<td><code>--trace-direction</code></td>
<td><code>auto</code> (right)</td>
<td><code>0</code> (left)</td>
</tr>
<tr>
<td><code>--sideboxes-direction</code></td>
<td><code>row</code></td>
<td><code>row-reverse</code></td>
</tr>
</tbody>
</table>



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
