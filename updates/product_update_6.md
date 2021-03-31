### The Fabric 6.4.2 official release

We have just released ***Fabric 6.4.2\*** . Some of the major enhancements of this release are listed below. To see full details, see the [Release Notes](https://eur03.safelinks.protection.outlook.com/?url=https%3A%2F%2Fwww.k2view.com%2Fe2t%2Ftc%2FVV_gJM652WNDW5YXSdk919MMqW7-75Mp4pR95-N4KxWLmcj98JV9V4KD7CgRjCW1glZ4j89pX87W5HvjMM1st5MbW8bGWyH5fj0RQW5S-2D67p5FPZW7tMq4r7P-zq4W5bL4Cq7_sfbZW6fdP0d4JGy5gW95yXCx8PshmlW8Cp1cg2rFSJWV4YgpD3G2yd0W7F9LqZ85S-x9W330spm2S6gLnW7zzQvn4_s8tBW35xzFn3F69xxW1BtT_P5JvCJQW1PcRhb7XY-nwN16QH0ynSv_yW2n7X_W36ZmpmVTVvQz923TzVW96DWRg4FMkDBW6-p3k77HvDncW2P0_Br68Sk_9VVCQhT8nMHwyVB7qyq8PN0f0V7t49p8rVpD6W6zhD_Q5PFKfNW3Wgzpg4DYlh0W5zgyQ492PPBHW5fmXdJ8h_4_kW3CXy_B94j-WzW1wKXQp6hXJYVW26qzg-15T7B7W6mfJFy4BDTXRW6096jy3NjB9vVDF8k_56mjdrW2SB_V03r0vNpW3Ms7BP198JHqW44SbLq1XW_Q8W7Jh1zp5tKrCLW1V9kHQ3KM8pPVT78q57YNdX-W3fJRdj3tnYHtW3XCBgL4k3QCWW74FCQy15w8_DW5tDCkB6Q_NdgW8s0ZLM95r81rW70GB-F2JRglXW51BMTv5XKK6QW78fjDy7CHPZqW471wtL55wDD5Mr5y1C5Z7L-V59wLB59hkftW6ZRsSc4ZXzmHW97zVGc3blwQ0W8kBN8w1mL_MTW4NTSBw5lb7z1W75TKgW5VCl_8N8Q5KRLMG0kTW6NhcQy311ZvrW6ql1-w79MtPxW2F-BKX4k9NspW5KVqVM64dc0hW208vH47SHfQKW7ggjt-8h5vFFVpdfTD6X9bZ3W5X-_hz8cyx7qW6xhYDc87qX0HW5NZMxL7FXhdGW4hPjKw3qQBDVW3MJyy02DZshSW1HZS8C3ZlW15W8mnB-t3n8dbnMN_jfQwywT-W59JV4s5NHMLfW4Nrpwp18vFF7W2v2KdG1_PMwFVMFkMt3S1l1DN7PvK1-XW-DRW57--bc3ffRwdN1jDjSLbMHWS318F1&data=04|01|lion.brotzky%40k2view.com|6021f7e4647449de562f08d8f4370a15|994f176e677549549f9e0c719b5e9ca0|1|0|637527864521020308|Unknown|TWFpbGZsb3d8eyJWIjoiMC4wLjAwMDAiLCJQIjoiV2luMzIiLCJBTiI6Ik1haWwiLCJXVCI6Mn0%3D|1000&sdata=XRXzv%2BxKzkUUZ%2FGu39etpqkZMU3xyInqgsvRHIsV77w%3D&reserved=0) :

 

Broadway

- The Subscribe Actor can now listen     to multiple topics by using regex in the topic argument.
- Many new Actors were added.

IIDFinder

- Added a new command -     delta_recycle. This recycles  transactions that failed in the delta PARTITION mode and should be recycled to Kafka     after deploying the fixed code.
       This new command supports prepared statements, so it can be executed by a     batch process as well.

- When IIDFinder is in the     delta PARTITION mode, priority instances are now handled in the same     topic/job as other instances that are not in priority.
       From the user point of view, there is no need to define a new job/topic     for priority instances.

Data Catalog

- Major performance improvements     were done with large projects.

Jobs

- Added an optimization mechanism     to ensure a better balancing of jobs within a Fabric cluster.

 

Miscellaneous

- A new command was added - set user_roles. This command returns the list     of roles of the connected user. 
- A new interface type was     introduced for SSH.
- Added the ability to run     parallel “gets” from a different LU type and to be able to run a merged     query on the same session.

 

And much more.

 

[Studio and Server Links](https://eur03.safelinks.protection.outlook.com/?url=https%3A%2F%2Fwww.k2view.com%2Fe2t%2Ftc%2FVV_gJM652WNDW5YXSdk919MMqW7-75Mp4pR95-N4KxWNrcj9bNV9V4KD7CgFQZW6Sb4c55WZVPwW6MZsQD6Nwg9QV6t9tv26cg2zW6mlQPQ4yKvz1W6Mp6Xh1D6Dn9W78S2rs1lPcyCW5x2X0p74yzcHW5YZn_H6hbP_nW220F8f62ZLDzN5V8yPppvqK3W2xh24F5f6L9gW5XqJr63V_lRnV-1NKG95jRVLW1-b1lD44BnbgW6y70FQ92kTMlW2mMv8N1l8YmqW98rcK12WDnz8W3LprVm4fmNNCW8sS1tW5J4NcsVLmdzl53TnwjW5RJV8n4qt-PZVdBf5P4qpzWVW8tK5Yq6SPZtyW5w3xN46xw2p1W36Jf-t7njG_tW4s4Q-_2BNNqqW1l3MMZ44ZQGmW8bv9Zx2qjH3wW7FPcL862xGd9W2hT-Zv2bZSp_W4v7r1P5qngSJW6FFwrg3sfX_NVBmtjb9456mMW7YFCXX2SKCJ5W9bLLhq3rSh9nW7cqgS524pVR8W1-Xq3l3B7MS6W2ZVbBc4NGvmyW7x9lsY3DwkCFW2hrh3Z7PQjVBW1B0Psh6wZMCsW1fP4xW3Ph9-YW94BhjD1HM3yPW3msn6-8_nHJPN1w4mFt74KCFW6y62B05TkVD3W1F4rRv5M7bFQW7_LY9b8xf5yVW7F1VJF4Wm_vGW71JG6-5rGpyjW5dy5b894b_yFW7C3b4t2Qt3SqN6DQy-SpNMrxN2ZqC1rW90CyW4ZHxzZ3Z7K2nW5KbClL4FYxKGW3-dsVG11hTNhW95Md549fS1K3W7j3RlP5HDSH_W6qJXnh50YGjqW3V9LB85XN7HwW1pJqZg6pGKwhW91kWWR3tnRm_W1MBpk515bZN6W6ncFJt6cq30YW2p6R6P7X3xDRN1rC4ybNkS62W6_LPB97FsnxGW81g8lv3L4pq3W9fVcCl3--MbrW8kHcDc4Gy738W4qrZ538bBQ1wN2MGdyFhq8g-N1LbdN-BXm5qW4JCzyP828RpzW6LJdBz8GNMd2W259F2R1Jr1LmW8jTcxF6chQ0wW3ZLk7k6VNGXcW7BqLsS7PHY73W1_vxRD6wG6dqW4WZw0T95GGkqW6DP0P66myZnmVN4XDs3VDn-3W4dSKVq6yX35nW3vK26M38pYmFW2xzHsP814St-W1fg2VJ8VkWfRW8kyD4x5PQTqpMxVGhSYrbHcW6gTkQM1NnTNfW52F8PJ1ZLHqDW4StdR15gzc6xVmHQXy67j9Q03q071&data=04|01|lion.brotzky%40k2view.com|6021f7e4647449de562f08d8f4370a15|994f176e677549549f9e0c719b5e9ca0|1|0|637527864521030299|Unknown|TWFpbGZsb3d8eyJWIjoiMC4wLjAwMDAiLCJQIjoiV2luMzIiLCJBTiI6Ik1haWwiLCJXVCI6Mn0%3D|1000&sdata=TZ0jSH9gY9aCxi9IFX7EckDM3ZC08s4JOM65wsCy9es%3D&reserved=0)


 Regards,
 K2view Product Team



![image](images/use_cases.png)