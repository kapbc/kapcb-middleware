local lockName = KEYS[1]
local lockValue = ARGV[1]
local lockReleaseTime = ARGV[2]
local result = redis.call('setNx', lockName, lockValue)
if result == 1 then
return redis.call('expire', lockName, lockReleaseTime)
else return 0
end
