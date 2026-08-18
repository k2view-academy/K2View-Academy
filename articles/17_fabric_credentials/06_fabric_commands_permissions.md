# Fabric Commands - Required Permissions

The table below lists every Fabric command and the permission(s) needed to run it. For what each permission actually allows, see the [List of Permissions](/articles/17_fabric_credentials/01_fabric_credentials_overview.md#list-of-permissions) in the Fabric Authorization overview article. For command syntax and usage examples, see [Fabric Authorization Commands](/articles/17_fabric_credentials/02_fabric_credentials_commands.md) and [Fabric Authorization List Commands](/articles/17_fabric_credentials/02a_fabric_credentials_list_commands.md).

<table>
  <thead>
    <tr><th>#</th><th>Fabric command</th><th>Required permission</th></tr>
  </thead>
  <tbody>
    <tr><td>1</td><td><code>ACTIVATEKEY</code></td><td><code>ACTIVATE_KEY</code></td></tr>
    <tr><td>2</td><td><code>ALTER USER</code></td><td>self → no permissions required; other users → <code>ALL</code></td></tr>
    <tr><td>3</td><td><code>ASSIGN ROLE</code></td><td><code>ASSIGN_ROLE</code> / <code>ALL</code></td></tr>
    <tr><td>4</td><td><code>ASSIGN SECURITY_PROFILE</code></td><td><code>ASSIGN_SECURITY_PROFILE</code></td></tr>
    <tr><td>5</td><td><code>BATCH</code></td><td><code>BATCH</code> on the target LU</td></tr>
    <tr><td>6</td><td><code>BATCH_DETAILS</code></td><td><code>BATCH</code> or <code>BATCH_READ</code></td></tr>
    <tr><td>7</td><td><code>BATCH_EDIT</code></td><td><code>ALL</code></td></tr>
    <tr><td>8</td><td><code>BATCH_IN_PROCESS</code></td><td><code>BATCH</code> or <code>BATCH_READ</code></td></tr>
    <tr><td>9</td><td><code>BATCH_INFO</code></td><td><code>BATCH</code> or <code>BATCH_READ</code></td></tr>
    <tr><td>10</td><td><code>BATCH_LIST</code></td><td><code>BATCH</code> or <code>BATCH_READ</code></td></tr>
    <tr><td>11</td><td><code>BATCH_PAUSE</code></td><td><code>BATCH</code></td></tr>
    <tr><td>12</td><td><code>BATCH_PROCESS_UNIT</code></td><td>none</td></tr>
    <tr><td>13</td><td><code>BATCH_RETRY</code></td><td><code>BATCH</code></td></tr>
    <tr><td>14</td><td><code>BATCH_SUMMARY</code></td><td><code>BATCH</code> or <code>BATCH_READ</code></td></tr>
    <tr><td>15</td><td><code>BROADWAY</code></td><td><code>BROADWAY</code></td></tr>
    <tr><td>16</td><td><code>CANCEL</code> (also <code>BATCH_CANCEL</code>)</td><td><code>BATCH</code></td></tr>
    <tr><td>17</td><td><code>CDC_REPUBLISH_INSTANCE</code></td><td><code>ALL</code></td></tr>
    <tr><td>18</td><td><code>CDC_REPUBLISH_SCHEMA</code></td><td><code>ALL</code></td></tr>
    <tr><td>19</td><td><code>CHECK_PERMISSION</code></td><td><code>ALL</code></td></tr>
    <tr><td>20</td><td><code>CLUSTERID</code></td><td><code>ALL</code></td></tr>
    <tr><td>21</td><td><code>CLUSTERSTATUS</code></td><td><code>ALL</code></td></tr>
    <tr><td>22</td><td><code>CQL</code></td><td><code>READ</code></td></tr>
    <tr><td>23</td><td><code>CREATE ROLE</code></td><td><code>ALL</code></td></tr>
    <tr><td>24</td><td><code>CREATE TOKEN</code></td><td><code>ALL</code></td></tr>
    <tr><td>25</td><td><code>CREATE USER</code></td><td><code>ALL</code></td></tr>
    <tr><td>26</td><td><code>DEBUG</code></td><td><code>ALL</code></td></tr>
    <tr><td>27</td><td><code>DELETE INSTANCE</code></td><td><code>DELETE_INSTANCE</code></td></tr>
    <tr><td>28</td><td><code>DELETE INSTANCES IF NOT EXIST</code></td><td><code>ALL</code></td></tr>
    <tr><td>29</td><td><code>DEPLOY</code></td><td><code>DEPLOY</code>, optional granularity - only on dedicated LUs</td></tr>
    <tr><td>30</td><td><code>DEPLOY ENVIRONMENTS FROM FILE</code></td><td><code>DEPLOY_ENVIRONMENTS</code></td></tr>
    <tr><td>31</td><td><code>DESCRIBE</code></td><td><code>READ</code> or <code>READ_WITH_CLAIM</code></td></tr>
    <tr><td>32</td><td><code>DROP</code> (<code>ROLE</code> / <code>USER</code> / <code>TOKEN</code> / <code>LUTYPE</code>)</td><td>no single gate. Varies per <code>DROP</code> type, see breakdown below</td></tr>
    <tr><td>33</td><td><code>GET</code></td><td><code>READ</code>, optional granularity - only on dedicated LUs/IID; <code>ALL</code>; <code>BATCH</code> if inside a batch process; <code>READ_WITH_CLAIM</code> with claims validation</td></tr>
    <tr><td>34</td><td><code>GETF</code></td><td>same as <code>GET</code></td></tr>
    <tr><td>35</td><td><code>GRANT</code></td><td><code>EDIT_ROLE</code> (or <code>ALL</code>), plus per-resource granularity (a role with <code>EDIT_ROLE</code> scoped to one LU cannot grant/revoke permissions on a different LU)</td></tr>
    <tr><td>36</td><td><code>HELP</code></td><td>none</td></tr>
    <tr><td>37</td><td><code>JOBSTATUS</code></td><td><code>ALL</code></td></tr>
    <tr><td>38</td><td><code>KILL</code></td><td><code>ALL</code></td></tr>
    <tr><td>39</td><td><code>LIST</code></td><td>no single gate. Varies per <code>LIST</code> type, see breakdown below</td></tr>
    <tr><td>40</td><td><code>MDB_EXPORT</code></td><td><code>READ</code></td></tr>
    <tr><td>41</td><td><code>MDB_IMPORT</code></td><td><code>WRITE</code></td></tr>
    <tr><td>42</td><td><code>MDB_SIZE</code></td><td>none</td></tr>
    <tr><td>43</td><td><code>MEMORY_USAGE</code></td><td><code>ALL</code></td></tr>
    <tr><td>44</td><td><code>MIGRATE</code></td><td><code>BATCH</code> (delegates internally to <code>BATCH</code>)</td></tr>
    <tr><td>45</td><td><code>MIGRATE_DETAILS</code></td><td><code>BATCH</code> or <code>BATCH_READ</code> (delegates to <code>BATCH_DETAILS</code>)</td></tr>
    <tr><td>46</td><td><code>MIGRATE_IN_PROCESS</code></td><td><code>BATCH</code> or <code>BATCH_READ</code> (delegates to <code>BATCH_IN_PROCESS</code>)</td></tr>
    <tr><td>47</td><td><code>MIGRATE_LIST</code></td><td><code>BATCH</code> or <code>BATCH_READ</code> (delegates to <code>BATCH_LIST</code>)</td></tr>
    <tr><td>48</td><td><code>MIGRATE_RESUME</code></td><td><code>BATCH</code> (delegates to <code>BATCH_RETRY</code>)</td></tr>
    <tr><td>49</td><td><code>MIGRATE_SUMMARY</code></td><td><code>BATCH</code> or <code>BATCH_READ</code> (delegates to <code>BATCH_SUMMARY</code>)</td></tr>
    <tr><td>50</td><td><code>PARSERSTATUS</code></td><td><code>ALL</code></td></tr>
    <tr><td>51</td><td><code>K2PROFILER</code></td><td><code>ALL</code></td></tr>
    <tr><td>52</td><td><code>PS</code></td><td><code>ALL</code></td></tr>
    <tr><td>53</td><td><code>REF_BACKUP</code></td><td><code>ALL</code></td></tr>
    <tr><td>54</td><td><code>REF_BACKUP_DELETE</code></td><td><code>ALL</code></td></tr>
    <tr><td>55</td><td><code>REF_BACKUP_DOWNLOAD</code></td><td><code>ALL</code></td></tr>
    <tr><td>56</td><td><code>REF_CANCEL</code></td><td><code>ALL</code></td></tr>
    <tr><td>57</td><td><code>REF_STATUS</code></td><td><code>ALL</code></td></tr>
    <tr><td>58</td><td><code>REF_SYNC</code></td><td><code>ALL</code></td></tr>
    <tr><td>59</td><td><code>REF_SYNC_WAIT</code></td><td><code>ALL</code></td></tr>
    <tr><td>60</td><td><code>REKEY_ENCRYPTED</code></td><td><code>ALL</code></td></tr>
    <tr><td>61</td><td><code>RELEASE</code></td><td>none</td></tr>
    <tr><td>62</td><td><code>RESTARTJOB</code></td><td><code>ALL</code></td></tr>
    <tr><td>63</td><td><code>RESTARTPARSER</code></td><td><code>ALL</code></td></tr>
    <tr><td>64</td><td><code>RESUMEJOB</code></td><td><code>ALL</code></td></tr>
    <tr><td>65</td><td><code>REVOKE</code></td><td><code>EDIT_ROLE</code> (or <code>ALL</code>), plus per-resource granularity (a role with <code>EDIT_ROLE</code> scoped to one LU cannot grant/revoke permissions on a different LU)</td></tr>
    <tr><td>66</td><td><code>REVOKE ROLE</code></td><td><code>REVOKE_ROLE</code></td></tr>
    <tr><td>67</td><td><code>REVOKE SECURITY_PROFILE</code></td><td><code>REVOKE_SECURITY_PROFILE</code></td></tr>
    <tr><td>68</td><td><code>SEARCH</code></td><td><code>READ</code></td></tr>
    <tr><td>69</td><td><code>SET</code></td><td>no single gate. Varies per option, see breakdown below</td></tr>
    <tr><td>70</td><td><code>SET_GLOBAL</code></td><td>
      <code>SET_GLOBAL_GLOBAL</code> - to set a <code>GLOBAL</code> or <code>AFFINITY_RULES</code> value cluster-wide.<br>
      <code>SET_GLOBAL_ENVIRONMENT</code> - to set the cluster's active environment.<br>
      <code>SET_GLOBAL_CONFIG_OVERRIDES</code> - to apply config overrides (<code>CONFIG_OVERRIDES</code> / <code>_ADD</code> / <code>_REMOVE</code> / <code>_VERSION</code>) cluster-wide.
    </td></tr>
    <tr><td>71</td><td><code>SHOW_DELTA</code></td><td><code>READ</code>, optional granularity - only on dedicated LUs</td></tr>
    <tr><td>72</td><td><code>STARTJOB</code></td><td><code>ALL</code></td></tr>
    <tr><td>73</td><td><code>STARTPARSER</code></td><td><code>ALL</code></td></tr>
    <tr><td>74</td><td><code>STOPJOB</code></td><td><code>ALL</code></td></tr>
    <tr><td>75</td><td><code>STOPPARSER</code></td><td><code>ALL</code></td></tr>
    <tr><td>76</td><td><code>STREAM_SYNC</code></td><td><code>ALL</code></td></tr>
    <tr><td>77</td><td><code>SYNC_INSTANCE</code></td><td>none</td></tr>
    <tr><td>78</td><td><code>TEST_CONNECTION</code></td><td><code>ALL</code></td></tr>
    <tr><td>79</td><td><code>TIME</code></td><td>none</td></tr>
    <tr><td>80</td><td><code>TRACE</code></td><td><code>ALL</code></td></tr>
    <tr><td>81</td><td><code>UPDATEJOB</code></td><td><code>ALL</code></td></tr>
    <tr><td>82</td><td><code>VERSION</code></td><td><code>ALL</code></td></tr>
  </tbody>
</table>

## `LIST` per-type breakdown

`LIST` has no single gate. Permission enforcement is done per `LIST` type:

<table>
  <thead><tr><th>LIST type</th><th>Required permission</th></tr></thead>
  <tbody>
    <tr><td><code>WS</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>DB_SOURCES</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>INTERFACES</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>LU_TYPES</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>LUT</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>ENVIRONMENTS</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>ENVS</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>INSTANCE_GROUPS</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>IGS</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>BROADWAY_FLOWS</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>BF</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>SECURITY_PROFILES</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>REF_BACKUPS</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>MTABLE</code></td><td><code>LIST_SETTINGS</code></td></tr>
    <tr><td><code>INSTANCES</code></td><td><code>READ</code>. Can be with granularity of per-LU read</td></tr>
    <tr><td><code>CONFIG</code></td><td><code>READ_CONFIG</code></td></tr>
    <tr><td><code>CONFIG_OVERRIDES</code></td><td><code>READ_CONFIG</code></td></tr>
    <tr><td><code>CONFIG_OVERRIDES_HISTORY</code></td><td><code>READ_CONFIG</code></td></tr>
    <tr><td><code>AFFINITY_RULES</code></td><td><code>READ_CONFIG</code></td></tr>
    <tr><td><code>ROLES</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>USERS</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>TOKENS</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>ROLE_PERMISSIONS</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>METHODS</code></td><td><code>ALL</code></td></tr>
  </tbody>
</table>

## `DROP` per-type breakdown

`DROP` has no single gate. Permission enforcement is done per each `DROP` type:

<table>
  <thead><tr><th><code>DROP &lt;type&gt;</code></th><th>Required permission</th></tr></thead>
  <tbody>
    <tr><td><code>ROLE</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>USER</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>TOKEN</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>LUTYPE</code></td><td><code>DROP_LUTYPE</code>, optional granularity per LU</td></tr>
  </tbody>
</table>

## `SET` per-option breakdown

`SET` has no single gate for most options. It has two shapes: *setting a global/LU-scoped variable by name* and *setting a session with a specific task*.

<table>
  <thead><tr><th><code>SET &lt;option/task&gt;</code></th><th>Required permission</th></tr></thead>
  <tbody>
    <tr><td><code>SYNC</code></td><td>none</td></tr>
    <tr><td><code>INSTANCE_TTL</code></td><td>none</td></tr>
    <tr><td><code>ENVIRONMENT</code></td><td><code>SET_ENVIRONMENT</code> OR <code>SET_GLOBAL_ENVIRONMENT</code></td></tr>
    <tr><td><code>DB_PROXY</code> (i.e. <code>SET DB_INTERFACE_PROXY</code>)</td><td><code>DB_INTERFACE_PROXY</code></td></tr>
    <tr><td><code>SYSTEM_DB_PROXY</code></td><td><code>SYSTEM_DB_PROXY</code></td></tr>
    <tr><td><code>CLUSTER_DISTRIBUTE_AFFINITY</code></td><td>any permission except <code>ALL_WS</code></td></tr>
    <tr><td><code>OUTPUT</code></td><td><code>ALL</code></td></tr>
    <tr><td><code>LOG_ID</code>, <code>ASYNC_TRX</code>, <code>COMMON_LOCAL_TRX</code>, <code>FROM</code>, <code>CDC_PUBLISH</code>, <code>NODE_AFFINITY</code>, and all other options</td><td>none</td></tr>
    <tr><td><code>SET &lt;name&gt;=&lt;value&gt;</code></td><td>none (a global/LU variable, not a session <code>Option</code>)</td></tr>
    <tr><td><code>SET</code> (no args — show all)</td><td>none for session values; <code>SET_READ</code> to also see globals</td></tr>
    <tr><td><code>PROJECT_NAME</code>, <code>USERNAME</code>, <code>USER_ROLES</code>, <code>SCOPE</code>, <code>DEFAULT</code>, <code>IS_STUDIO</code></td><td>none (Read-only/unchangeable)</td></tr>
  </tbody>
</table>

[![Previous](/articles/images/Previous.png)](/articles/17_fabric_credentials/01_fabric_credentials_overview.md)
