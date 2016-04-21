import os
import sys
import json
import time 

#private information
security_group_id=""
sdf_vol_id=""
sdg_vol_id=""
keyfile_path=""

instance_type = sys.argv[1]
instance_name = sys.argv[2]

print "Creating a " + instance_type + " machine of name: " + instance_name

run_instances_cmd = "aws ec2 run-instances --image-id ami-9abea4fb --count 1 --key-name 572_keypair --security-group-ids " + security_group_id + " --subnet-id subnet-041adc73 --instance-type " + instance_type;
cmd_out = os.popen(run_instances_cmd).read()
json_data = json.loads(cmd_out)

instances = json_data["Instances"]
instance = instances[0]
instance_id = instance["InstanceId"]

print "Created the instance: " + instance_id
time.sleep(30)

attach_cmd = 'aws ec2 attach-volume --volume-id ' + sdf_vol_id + ' --device "/dev/sdf" --instance-id ' + instance_id
cmd_out = os.popen(attach_cmd).read()
json_data = json.loads(cmd_out)

print "Attached " + json_data["VolumeId"] + " to " + json_data["InstanceId"] + " at " + json_data["Device"]

attach_cmd = 'aws ec2 attach-volume --volume-id ' + sdg_vol_id + ' --device "/dev/sdg" --instance-id ' + instance_id
cmd_out = os.popen(attach_cmd).read()
json_data = json.loads(cmd_out)

print "Attached " + json_data["VolumeId"] + " to " + json_data["InstanceId"] + " at " + json_data["Device"]

tagging_cmd = 'aws ec2 create-tags --tags Key=Name,Value=' + instance_name + ' --resources ' + instance_id
cmd_out = os.popen(tagging_cmd).read()
json_data = json.loads(cmd_out)

describe_cmd = 'aws ec2 describe-instances --instance-ids ' + instance_id
cmd_out = os.popen(describe_cmd).read()
json_data = json.loads(cmd_out)
dnsName = json_data["Reservations"][0]["Instances"][0]["PublicDnsName"]

#let instance initialize, otherwise ssh fails
time.sleep(100)
script_runner = "ssh -oStrictHostKeyChecking=no -i " + keyfile_path + " ubuntu@" + dnsName + " 'bash -s' < DS_Post_Attach_Setup.sh"
cmd_out = os.popen(script_runner).read()
print "Post attach script RUN"
print cmd_out

script_runner = "ssh -oStrictHostKeyChecking=no -i " + keyfile_path + " ubuntu@" + dnsName + " 'bash -s' < DS_Transfer_Setup.sh"
cmd_out = os.popen(script_runner).read()
print "Post attach script RUN"
print cmd_out
 
print "SUMMARY: login: ssh -i " + keyfile_path + " ubuntu@" + dnsName
