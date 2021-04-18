### The Fabric 6.4.2 Official Release
We have just released Fabric 6.4.2. Some of the major enhancements of this release are listed below. All the details can be found in the [Release Notes](https://support.k2view.com/Academy_6.4/Release%20Notes/V6.4/Fabric_Release%20Notes%20V6.4.2.pdf.html):
- Many new Actors were added.
- Added a **delta_recycle** command  that recycles transactions failed in the delta PARTITION mode and should be recycled to Kafka after deploying the fixed code.
- When iidFinder is in the delta PARTITION mode, priority instances are now handled in the same topic/job as other instances that are not in priority.
   - From the user point of view, there is no need to define a new job/topic for priority instances.   
- Added an optimization mechanism to ensure a better balancing of jobs within a Fabric cluster.
- A new interface type was introduced for SSH.
- Added the ability to run parallel “gets” from different LU types and to be able to run a merged     query on the same session.

![image](images/use_cases.png)
