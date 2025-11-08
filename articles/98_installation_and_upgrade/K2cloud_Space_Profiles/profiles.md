# K2cloud Versionless Space Profile Naming Guide

This guide explains how to read the names of K2cloud’s **versionless** Space Profiles. These profiles standardize sizing across environments and are available out-of-the-box (OOTB).

* **Existing customers:** ask us to enable these profiles on your tenant.
* **New customers:** profiles are available at provisioning.

---

## How to read a profile name

**Pattern**

```
<Product>-c<CPUs>-m<MemoryGB>[-r<Replicas> | -r<Min>-<Max>][-pg | -mngd | -noSdB]
```

**Tokens**

* `Product` — **Studio** or **Fabric**.
* `c#` — vCPU per pod (e.g., `c4` = 4 vCPU).
* `m#` — memory (GB) per pod (e.g., `m32` = 32 GB).
* `r#` — fixed replica count for **Fabric** (Studio is single-pod).
* `r<Min>-<Max>` — **autoscale** replica range for Fabric (min/max).
* `-pg` — Studio only: includes an embedded PostgreSQL system DB.
* `-mngd` — Fabric only: uses a **managed PostgreSQL** service + object storage bucket.
* `-noSdB` — Fabric only: **no system DB** is provisioned by K2cloud.

> Storage classes are cloud-aware and set via your Foundation (e.g., GCP `regional-pd`, Azure `managed`).

---

## Examples

* `Studio-c4-m32` → Studio, 4 vCPU / 32 GB, 1 pod, **SQLite** system DB.
* `Studio-c4-m32-pg` → Studio, 4 vCPU / 32 GB, 1 pod, **embedded PostgreSQL** system DB.
* `Fabric-c8-m64-r3-noSdB` → Fabric, 8 vCPU / 64 GB per pod, **3 replicas**, **no** system DB.
* `Fabric-c4-m32-r5-mngd` → Fabric, 4 vCPU / 32 GB per pod, **5 replicas**, **managed PG + bucket**.
* `Fabric-c2-m16-r2-4-mngd` → Fabric, 2 vCPU / 16 GB, **autoscale 2–4 replicas**, managed PG + bucket.

---

## Catalog of current profiles

### Studio

<table>
  <thead>
    <tr>
      <th>Space profile name</th>
      <th style="text-align:right;">CPU</th>
      <th style="text-align:right;">Mem (GB)</th>
      <th style="text-align:right;">Replicas</th>
      <th>Extra components</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Studio-c2-m16</td>
      <td style="text-align:right;">2</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">1</td>
      <td>sys DB – <strong>SQLite</strong></td>
    </tr>
    <tr>
      <td>Studio-c4-m32</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">1</td>
      <td>sys DB – <strong>SQLite</strong></td>
    </tr>
    <tr>
      <td>Studio-c8-m64</td>
      <td style="text-align:right;">8</td>
      <td style="text-align:right;">64</td>
      <td style="text-align:right;">1</td>
      <td>sys DB – <strong>SQLite</strong></td>
    </tr>
    <tr>
      <td>Studio-c2-m16-pg</td>
      <td style="text-align:right;">2</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">1</td>
      <td>sys DB – <strong>PostgreSQL</strong></td>
    </tr>
    <tr>
      <td>Studio-c4-m32-pg</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">1</td>
      <td>sys DB – <strong>PostgreSQL</strong></td>
    </tr>
    <tr>
      <td>Studio-c8-m64-pg</td>
      <td style="text-align:right;">8</td>
      <td style="text-align:right;">64</td>
      <td style="text-align:right;">1</td>
      <td>sys DB – <strong>PostgreSQL</strong></td>
    </tr>
  </tbody>
</table>

### Fabric (no system DB)

<table>
  <thead>
    <tr>
      <th>Space profile name</th>
      <th style="text-align:right;">CPU</th>
      <th style="text-align:right;">Mem (GB)</th>
      <th style="text-align:right;">Replicas</th>
      <th>Extra components</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Fabric-c2-m16-r1-noSdB</td>
      <td style="text-align:right;">2</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">1</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c2-m16-r2-noSdB</td>
      <td style="text-align:right;">2</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">2</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c4-m32-r1-noSdB</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">1</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c4-m32-r3-noSdB</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">3</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c4-m32-r5-noSdB</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">5</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c8-m64-r3-noSdB</td>
      <td style="text-align:right;">8</td>
      <td style="text-align:right;">64</td>
      <td style="text-align:right;">3</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c8-m64-r5-noSdB</td>
      <td style="text-align:right;">8</td>
      <td style="text-align:right;">64</td>
      <td style="text-align:right;">5</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c16-m128-r3-noSdB</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">128</td>
      <td style="text-align:right;">3</td>
      <td>no system DB</td>
    </tr>
    <tr>
      <td>Fabric-c16-m128-r5-noSdB</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">128</td>
      <td style="text-align:right;">5</td>
      <td>no system DB</td>
    </tr>
  </tbody>
</table>

### Fabric (managed Postgres + bucket)

<table>
  <thead>
    <tr>
      <th>Space profile name</th>
      <th style="text-align:right;">CPU</th>
      <th style="text-align:right;">Mem (GB)</th>
      <th style="text-align:right;">Replicas</th>
      <th>Extra components</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Fabric-c2-m16-r1-mngd</td>
      <td style="text-align:right;">2</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">1</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c2-m16-r2-mngd</td>
      <td style="text-align:right;">2</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">2</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c4-m32-r1-mngd</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">1</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c4-m32-r3-mngd</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">3</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c4-m32-r5-mngd</td>
      <td style="text-align:right;">4</td>
      <td style="text-align:right;">32</td>
      <td style="text-align:right;">5</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c8-m64-r3-mngd</td>
      <td style="text-align:right;">8</td>
      <td style="text-align:right;">64</td>
      <td style="text-align:right;">3</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c8-m64-r5-mngd</td>
      <td style="text-align:right;">8</td>
      <td style="text-align:right;">64</td>
      <td style="text-align:right;">5</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c16-m128-r3-mngd</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">128</td>
      <td style="text-align:right;">3</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
    <tr>
      <td>Fabric-c16-m128-r5-mngd</td>
      <td style="text-align:right;">16</td>
      <td style="text-align:right;">128</td>
      <td style="text-align:right;">5</td>
      <td><strong>managed PG + bucket</strong></td>
    </tr>
  </tbody>
</table>

---

## Autoscale naming (Fabric)

When autoscaling is enabled, the replica token shows the **minimum and maximum** replicas:

* **Format:** `-r<Min>-<Max>`
* **Example:** `Fabric-c2-m16-r2-4-mngd` → min **2**, max **4** replicas.

---

## Choosing a profile (quick pointers)

* **Studio**: start with `c2/m16` or `c4/m32`; use `-pg` if you prefer a PostgreSQL system DB over SQLite.
* **Fabric**: use `-mngd` when you want cloud-managed PostgreSQL + object storage; choose `-noSdB` when your space will not provision a system DB.
* Use higher `c/m` or more replicas for heavier workloads or stricter HA.

---

If you’d like these profiles enabled on your tenant—or need autoscale variants—please open a ticket with the profile names you want.
