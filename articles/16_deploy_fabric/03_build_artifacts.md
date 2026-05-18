# Build Artifacts

Building a Fabric project compiles the Java sources of each Logical Unit and packages them into deployment artifacts. The output per LU is:

- **ludb.jar** — compiled Java bytecode
- **ludbXMLs.zip** — LU configuration and schema metadata

These artifacts are the input for the deployment phase. They can be built and deployed in one step, or built once and deployed to multiple environments separately.

> CI/CD pipelines should build artifacts on a dedicated build server using a Docker image that contains Fabric. Fabric itself does not need to be running to build artifacts.

<studio>

## Build from Fabric Studio

To build artifacts in the Fabric Studio:

1. Right-click the **object** (for example, **Web Services**) and click **Build Deploy Artifacts**. A notification displays following a successful build.

<img src="images/16_03_offline_deploy1.png" alt="Build Deploy Artifacts" style="zoom: 80%;" />

2. Right-click the same **object** and select **Open Folder**. Windows Explorer opens at:  
   `[Your PC Folder]\K2View Fabric Studio\Projects\[Project Name]\Implementation\LogicalUnits\[LU Name]`

3. Locate the generated **ludb.JAR** and **ludbXMLs.ZIP** files.

</studio>

## Build on a Server using buildArtifacts.sh

Run `buildArtifacts.sh` on a server to compile and package the project artifacts. The script is located under `$K2_HOME/fabric/scripts`.

**Usage:**

~~~
./buildArtifacts.sh -pd <PATH_TO_PROJECT> [options]
~~~

**Options:**

<table>
<thead>
<tr>
<th><p><strong>Option</strong></p></th>
<th><p><strong>Description</strong></p></th>
<th><p><strong>Mandatory</strong></p></th>
<th><p><strong>Default</strong></p></th>
</tr>
</thead>
<tbody>
<tr>
<td><p>-pd / --project-dir</p></td>
<td><p>Path to the project directory. The folder name must match the .k2proj file name.</p></td>
<td><p>Y</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-l / --lu-type-name</p></td>
<td><p>LU name(s) to build. Accepts a comma-separated list (e.g., <code>-l Customer,Orders</code>).</p></td>
<td><p>N</p></td>
<td><p>All LUs in the project</p></td>
</tr>
<tr>
<td><p>-d / --output-direction</p></td>
<td><p>Output directory for generated artifacts.</p></td>
<td><p>N</p></td>
<td><p>&lt;project&gt;/Implementation/LogicalUnits</p></td>
</tr>
<tr>
<td><p>-pn / --project-name</p></td>
<td><p>Project name override.</p></td>
<td><p>N</p></td>
<td><p>Project folder name</p></td>
</tr>
<tr>
<td><p>-v / --jdk-version</p></td>
<td><p>JDK version for compilation.</p></td>
<td><p>N</p></td>
<td><p>21</p></td>
</tr>
<tr>
<td><p>-h / --help</p></td>
<td><p>Displays usage information.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
</tbody>
</table>

**Example — build all LUs:**

~~~bash
./buildArtifacts.sh -pd /opt/apps/MyProject
~~~

**Example — build specific LUs into a custom output directory:**

~~~bash
./buildArtifacts.sh -pd /opt/apps/MyProject -l Customer,Orders -d /tmp/artifacts
~~~

After a successful build, each LU folder under the output directory contains `ludb.jar` and `ludbXMLs.zip`.

## Next Step

Once artifacts are built, deploy them to the target Fabric server. See [Deploy a Project](/articles/16_deploy_fabric/04_deploy_project.md).



[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/04_deploy_project.md)
