-- -- 获取方法签名特征
-- local methodKey = KEYS[1]
-- redis.log(redis.LOG_DEBUG, 'key is', methodKey)
-- -- 限流的阈值
-- local limit = tonumber(ARGV[1])
-- 当前流量大小
-- local count = tonumber(redis.call('get', methodKey) or "0")
--
-- -- 是否超出限流标准
-- if count + 1 > limit then
--     return false
-- else
--     -- 设置当前访问数量 + 1
--     redis.call("INCRBY", methodKey, 1)
--     -- 设置过期时间
--     redis.call("EXPIRE", methodKey, 1)
--     return true
-- end


local key = KEYS[1]
local period = ARGV[1]
local limit= ARGV[2]
local times = redis.call('incr',key)

if times == 1 then
    redis.call('expire',KEYS[1], period)
end

if times > tonumber(limit) then
    return 0
end
return 1


local key = KEYS[1]
local limit = tonumber(ARGV[1]);
local count = tonumber(redis.call('get', key) or "0")

if count + 1 > limit
    return false
else
    redis.call("INCRBY", key, 1);
    redis.call("expire", key, ARGV[2]);
    return true
end