local lockName = KEYS[1]
local lockValue = ARGV[1]
local result = redis.call('get', lockName)
if result == lockValue then
return redis.call('del', lockName)
else return 0
end
