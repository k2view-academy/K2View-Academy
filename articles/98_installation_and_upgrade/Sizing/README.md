# K2view Platform Sizing Considerations: “It Depends”  

When sizing the K2view platform, there is no universal prescription. The right configuration always depends on the use case, workload profile, and performance expectations. Different scenarios stress different resources — memory, CPU, I/O, network, or external services. The allocation of worker nodes, the configuration of Fabric workers, and even the size of connection pools are all influenced by these considerations.  

---

**Fabric Web Studio Sizing Considerations**

Fabric Web Studio is the environment where developers design and maintain data products. The dominant sizing factor here is not the data itself, but the number of concurrent users and the complexity of their work. Each Studio space requires a dedicated share of memory and CPU, with around 8 GB RAM per space being a practical guideline. Larger teams or multiple active projects will drive the need for additional Studio spaces and, consequently, more worker nodes.  

It is also important to account for orchestration overhead. Deployments on Kubernetes or Docker Compose must reserve resources for container scheduling and service resilience. The result is that Web Studio environments scale primarily with developer concurrency rather than data volume, and sizing should reflect that reality.  

---

**Fabric Sizing Considerations**

Fabric is the runtime engine that powers real-time data products, and its sizing depends heavily on workload type.  

When Fabric is used for *data exposure*, the main concern is API throughput. Payload size, caching strategy, and the number of concurrent threads directly influence how many calls a cluster can sustain. Sizing is a matter of balancing those factors and adding nodes to meet expected transaction volumes.  

In *change data capture (CDC)* scenarios, Fabric must keep pace with the rate of change from source systems. The volume and velocity of CDC messages, along with the way Kafka consumer threads are tuned, define the necessary cluster capacity.  

As a *system of record (SOR)*, Fabric assumes responsibility for inserts, updates, and reads. The workload profile—whether dominated by reads, writes, or a balance of both—determines the CPU and I/O requirements.  

For *data pipelines and masking*, performance depends on the complexity of the transformations applied and the throughput of the source and target systems. Masking operations, particularly when handling personally identifiable information, can be CPU-intensive. Network performance also becomes a key factor when data is streamed across environments.  

The common thread across all Fabric use cases is that scaling is linear. More nodes provide more throughput. What differs is the factor that dominates—transaction volume, change velocity, workload balance, or transformation complexity.  

---

**TDM Sizing Considerations**

Test Data Management (TDM) builds on Fabric to extract, load, clone, and generate synthetic data. Here, performance is best measured in terms of business entities processed per second.  

Extract operations depend on the throughput of the source database and the degree of parallelism configured in the system. Load operations, in turn, depend on the capacity of the target database to ingest data and on the number of loader threads allocated.  

Cloning workloads are shaped by the complexity of entity synthesis, which can increase CPU demand. Rules-based synthetic data generation depends on the intricacy of the rules applied and tends to be CPU-bound, benefiting from additional worker threads. For AI-based synthetic data generation, batch size, worker allocation, and CPU/GPU memory all play a role in throughput.  

Full-table extract, mask, and load scenarios are influenced by the number of masked columns, the complexity of the masking logic, and the performance of both source and target systems.  

In practice, TDM sizing is a balancing act. It reflects not just entity volumes, but the mix of extract, load, cloning, and synthetic generation operations, each of which stresses different aspects of the infrastructure.  

---

**GenAI Data Fusion**

GenAI Data Fusion extends Fabric’s data exposure capabilities to support retrieval-augmented workflows for large language models. The Fabric platform is responsible for retrieving structured and unstructured data, applying masking and enrichment, and preparing the context that is ultimately provided to an LLM.  

The sizing considerations here mirror those of data exposure. API concurrency and latency expectations set the baseline for capacity. Additional overhead arises from enrichment steps and vector database lookups, which must be factored into CPU and memory allocation plans. Importantly, GPUs are not required within the Fabric environment itself. GPU acceleration is handled by the external LLM service (such as AWS Bedrock or Azure OpenAI).  

In other words, sizing for GenAI Data Fusion is about ensuring Fabric can orchestrate queries, apply transformations, and manage context delivery efficiently, while leaving AI model execution to the LLM provider.  

---

**Conclusion**

In every case, the answer to sizing begins with “it depends.”  

*Fabric Web Studio environments* depend on the number of concurrent developers.  

*Fabric runtime environments* depend on the type of workload—API exposure, CDC, SOR, or pipelines.  

*TDM environments* depend on the balance of extract, load, cloning, and synthetic data generation tasks.  

*GenAI Data Fusion environments* depend on exposure-like throughput requirements and the added cost of enrichment, while offloading AI execution to external services.  

K2view’s architecture supports linear scalability: workloads can be expanded by adding nodes as demand grows. The challenge is not deciding whether the system can scale, but understanding which factor—transaction volume, change velocity, workload mix, or enrichment complexity—will dominate in your environment.  
