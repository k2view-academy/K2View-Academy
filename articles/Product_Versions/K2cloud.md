# K2cloud Updates

## Table of Contents

* [K2cloud Releases](#k2cloud-releases)
* [K2cloud Agent, Deployer, and Helm Chart Updates](#k2cloud-agent-deployer-and-helm-chart-updates)
  * [Tracking Agent and Deployer Updates](#tracking-agent-and-deployer-updates)
  * [Deployer Update Requirement](#deployer-update-requirement)
  * [K2agent Releases](#k2agent-releases)
  * [K2deployer Releases](#k2deployer-releases)
  * [Helm Charts](#helm-charts)

<br><br>
# K2cloud Releases
<table>
  <thead>
    <tr>
      <th>K2cloud Release</th>
      <th>Date</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><a href="/Release_Notes/K2cloud/K2cloud_Release_Notes_Jan2026.pdf">K2cloud January 2026</a></td>
      <td>19 January 2026, 22:00 UTC</td>
    </tr>
    <tr>
      <td><a href="/Release_Notes/K2cloud/K2cloud_Release_Notes_Nov2025.pdf">K2cloud November 2025</a></td>
      <td>2 November 2025, 11:UTC</td>
    </tr>
    <tr>
      <td><a href="/Release_Notes/K2cloud/K2cloud_Release_Notes_Jan2025.pdf">K2cloud January 2025</a></td>
      <td>2 February 2025, 11:00 UTC</td>
    </tr>
  </tbody>
</table>

# K2cloud Agent, Deployer, and Helm Chart Updates

## Tracking Agent and Deployer Updates

Agent and Deployer images are updated periodically. Customers are responsible for keeping these components up to date.
Current K2cloud versions, release notes, and recent Agent and Deployer image tags are published on this page. We recommend reviewing this information regularly to stay aligned with supported versions.

## Deployer Update Requirement
If you pull a new Deployer image and push it to your own OCI registry, you must notify K2view so K2cloud can reference the updated image.

How to notify us:

* Open a support ticket
* Provide the Deployer image registry location and tag


## K2agent Releases
<table>
  <thead>
    <tr>
      <th>Tags</th>
      <th>Digest</th>
      <th>Last Modified</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>latest</td>
      <td>sha256:1fe78d337448a06a910439e9be9db71e5f442213d82e8e531f684399b8cfa6da</td>
      <td>11/12/2025, 9:01 AM UTC</td>
    </tr>
    <tr>
      <td>2.11.1</td>
      <td>sha256:1fe78d337448a06a910439e9be9db71e5f442213d82e8e531f684399b8cfa6da</td>
      <td>11/12/2025, 9:01 AM UTC</td>
    </tr>
    <tr>
      <td>2.11</td>
      <td>sha256:ad123f79f0d7788a2690c270bd4596d463cf2a2b943440283b09e165d3973876</td>
      <td>11/3/2025, 1:52 PM UTC</td>
    </tr>
    <tr>
      <td>2.10</td>
      <td>sha256:5006873d1f5e958c1aa6da5a983ec01db911bcc6b37a60fa6a15ca3aaa209257</td>
      <td>11/3/2025, 1:52 PM UTC</td>
    </tr>
  </tbody>
</table>

**Pull Instructions**
<br>
docker pull docker.share.cloud.k2view.com/k2view/k2v-agent:[tag]

## K2deployer Releases
<table>
  <thead>
    <tr>
      <th>Tags</th>
      <th>Digest</th>
      <th>Last Modified</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>latest</td>
      <td>sha256:7e7be948861efa06ce4f9c10db04c81f8889333816bbc96dea74bf58f386d3f5</td>
      <td>1/11/2026, 11:24 AM UTC</td>
    </tr>
    <tr>
      <td>1.8.23</td>
      <td>sha256:7e7be948861efa06ce4f9c10db04c81f8889333816bbc96dea74bf58f386d3f5</td>
      <td>1/11/2026, 11:24 AM UTC</td>
    </tr>
    <tr>
      <td>1.8.22</td>
      <td>sha256:d6f9e459534bd56ded1bc18aaace62d7dcabc58a786f21d08ce06375ae5a3cb4</td>
      <td>1/11/2026, 11:20 AM UTC</td>
    </tr>
    <tr>
      <td>1.8.21</td>
      <td>sha256:7bfbbeee1ecf132e5ebe1cb97f831bf4d0fd8840dd92b7b4e81a73f1714cca78</td>
      <td>1/4/2026, 9:28 AM UTC</td>
    </tr>
    <tr>
      <td>1.8.20</td>
      <td>sha256:7e67dc353fcf0f203789af19e85cc7a134ed79a6099610a2ad886d4fda3558fc</td>
      <td>12/23/2025, 2:13 PM UTC</td>
    </tr>
    <tr>
      <td>1.8.19</td>
      <td>sha256:3e3b86b28cd462dfa570661cc4bd23efa780a0462ae9dd5e5d9f61c3b2b18f2a</td>
      <td>12/18/2025, 7:29 PM UTC</td>
    </tr>
    <tr>
      <td>1.8.18</td>
      <td>sha256:5f0302c7d669c3940a720a111d20fb05b23e5c01dac1738189c9edb2e90b99ed</td>
      <td>12/16/2025, 2:23 PM UTC</td>
    </tr>
    <tr>
      <td>1.8.17</td>
      <td>sha256:b8276afc6f73c6ee1ac8b93917c1262b292252de1ef58e1e8a1c2b1952430480</td>
      <td>11/23/2025, 8:00 AM UTC</td>
    </tr>
    <tr>
      <td>1.8.16</td>
      <td>sha256:506077508139e6b1eaebffbd6c3145be78424cd7d621bb2edfa646bd59bcacfa</td>
      <td>11/6/2025, 8:48 AM UTC</td>
    </tr>
  </tbody>
</table>

**Pull Instructions**
<br>
docker pull docker.share.cloud.k2view.com/k2view/k2-cloud-deployer:[tag]

After you load the Deployer image to your OCI registry, remember to notify K2view of the new location. See  [Deployer Update Requirement](#deployer-update-requirement). 



## Helm Charts

K2cloud Helm charts are updated periodically and are maintained in GitHub:

https://github.com/k2view/blueprints/tree/main/helm

You can track Helm chart changes using:

* Commit history & timestamps in GitHub
* Commit activity graph: https://github.com/k2view/blueprints/graphs/commit-activity
* RSS feed (recommended): https://github.com/k2view/blueprints/commits/main.atom

Customers should review Helm chart updates as part of regular K2cloud maintenance.

