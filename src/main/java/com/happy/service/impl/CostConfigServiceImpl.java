package com.happy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.happy.dao.CostConfigDao;
import com.happy.dao.FloorDao;
import com.happy.dao.ParkingSpaceDao;
import com.happy.entity.Response;
import com.happy.entity.bo.CostConfigModeBo;
import com.happy.entity.dto.CostConfigDto;
import com.happy.entity.bo.CostConfigBo;
import com.happy.entity.dto.CostConfigModeDto;
import com.happy.entity.po.CostConfigPo;
import com.happy.entity.vo.CostConfigModeVo;
import com.happy.service.BaseService;
import com.happy.service.CostConfigService;
import com.happy.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 * @Title: CostConfigServiceImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/19 20:14
 */

@Service
public class CostConfigServiceImpl extends BaseService implements CostConfigService {

    private static int[] userTypeArr = new int[]{1,2,3};
    private static int[] parkTypeArr = new int[]{1,2,3};

    @Autowired
    private RedisService redisService;

    @Autowired
    private ParkingSpaceDao parkingSpaceDao;

    @Autowired
    private CostConfigDao costConfigDao;

    @Autowired
    private FloorDao floorDao;

    @Override
    @Transactional
    public Response setCostConfig(CostConfigDto costConfigDto) {

        List<CostConfigBo> costConfigBoList = setData(costConfigDto);
        setCostConfigPrice(costConfigDto);
        parkingSpaceDao.updateParkingSpacePrice(costConfigBoList);

        String floorIds = costConfigDto.getFloorIds();
        List<String> floorIdList = JSONArray.parseArray(floorIds, String.class);
        String unitIds = costConfigDto.getUnitIds();
        List<String> unitIdList = JSONArray.parseArray(unitIds, String.class);
        CostConfigModeBo costConfigModePropertyBo = new CostConfigModeBo();
        costConfigModePropertyBo.setPropertyPrice(costConfigDto.getPropertyPrice());
        costConfigModePropertyBo.setCommunityId(costConfigDto.getCommunityId());
        costConfigModePropertyBo.setFloorIdList(floorIdList);
        costConfigModePropertyBo.setFloorId(floorIdList.get(0));
        costConfigModePropertyBo.setUnitIdList(unitIdList);
        costConfigDao.updatePropertyPrice(costConfigModePropertyBo);

        CostConfigModeBo costConfigModeWasteBo = new CostConfigModeBo();
        costConfigModeWasteBo.setWastePrice(costConfigDto.getWastePrice());
        costConfigModeWasteBo.setCommunityId(costConfigDto.getCommunityId());
        costConfigModeWasteBo.setFloorIdList(floorIdList);
        costConfigModeWasteBo.setFloorId(floorIdList.get(0));
        costConfigModeWasteBo.setUnitIdList(unitIdList);
        costConfigDao.updateWastePrice(costConfigModeWasteBo);

        CostConfigModeBo costConfigModeEnergyBo = new CostConfigModeBo();
        costConfigModeEnergyBo.setEnergyPrice(costConfigDto.getEnergyOvertPrice());
        costConfigModeEnergyBo.setCommunityId(costConfigDto.getCommunityId());
        costConfigModeEnergyBo.setFloorIdList(floorIdList);
        costConfigModeEnergyBo.setFloorId(floorIdList.get(0));
        costConfigModeEnergyBo.setUnitIdList(unitIdList);
        costConfigDao.updateEnergyPrice(costConfigModeEnergyBo);

        CostConfigModeBo costConfigModeElevatorBo = new CostConfigModeBo();
        costConfigModeElevatorBo.setElevatorPrice(costConfigDto.getElevatorPrice());
        costConfigModeElevatorBo.setCommunityId(costConfigDto.getCommunityId());
        costConfigModeElevatorBo.setFloorIdList(floorIdList);
        costConfigModeElevatorBo.setFloorId(floorIdList.get(0));
        costConfigModeElevatorBo.setUnitIdList(unitIdList);
        Double elevatorPrice = costConfigDto.getElevatorPrice();
        Double elevatorIncrease = costConfigDto.getElevatorIncrease();
        if (!elevatorPrice.equals(0D)) {
            costConfigModeElevatorBo.setElevatorIncrease(elevatorIncrease/elevatorPrice);
        }
        else {
            costConfigModeElevatorBo.setElevatorIncrease(0d);
        }
        costConfigModeElevatorBo.setElevatorStartLayer(costConfigDto.getElevatorStartLayer());
        costConfigDao.updateElevatorPrice(costConfigModeElevatorBo);

        CostConfigModeBo costConfigModeWaterBo = new CostConfigModeBo();
        costConfigModeWaterBo.setWaterPrice(costConfigDto.getWaterPrice());
        costConfigModeWaterBo.setCommunityId(costConfigDto.getCommunityId());
        costConfigModeWaterBo.setFloorIdList(floorIdList);
        costConfigModeWaterBo.setFloorId(floorIdList.get(0));
        costConfigModeWaterBo.setUnitIdList(unitIdList);
        costConfigDao.updateWaterPrice(costConfigModeWaterBo);

        CostConfigModeBo costConfigModePowerBo = new CostConfigModeBo();
        costConfigModePowerBo.setPowerPrice(costConfigDto.getPowerPrice());
        costConfigModePowerBo.setCommunityId(costConfigDto.getCommunityId());
        costConfigModePowerBo.setFloorIdList(floorIdList);
        costConfigModePowerBo.setFloorId(floorIdList.get(0));
        costConfigModePowerBo.setUnitIdList(unitIdList);
        costConfigDao.updatePowerPrice(costConfigModePowerBo);

        CostConfigModeBo costConfigModeHeatBo = new CostConfigModeBo();
        costConfigModeHeatBo.setHeatPrice(costConfigDto.getHeatPrice());
        costConfigModeHeatBo.setCommunityId(costConfigDto.getCommunityId());
        costConfigModeHeatBo.setFloorIdList(floorIdList);
        costConfigModeHeatBo.setFloorId(floorIdList.get(0));
        costConfigModeHeatBo.setUnitIdList(unitIdList);
        costConfigDao.updateHeatPrice(costConfigModeHeatBo);
        return buildSuccesResponse();
    }

    @Override
    @Transactional
    public Response setCostConfigMode(CostConfigModeDto costConfigModeDto) {

        setCostConfigType(costConfigModeDto);

        CostConfigModeBo propertyCostConfigModeBo = new CostConfigModeBo();
        propertyCostConfigModeBo.setCommunityId(costConfigModeDto.getCommunityId());
        propertyCostConfigModeBo.setChargeType(costConfigModeDto.getPropertyType());
        propertyCostConfigModeBo.setStandard(costConfigModeDto.getPropertyConfigName());
        costConfigDao.updateProperty(propertyCostConfigModeBo);


        CostConfigModeBo wasteCostConfigModeBo = new CostConfigModeBo();
        wasteCostConfigModeBo.setCommunityId(costConfigModeDto.getCommunityId());
        wasteCostConfigModeBo.setChargeType(costConfigModeDto.getWasteType());
        wasteCostConfigModeBo.setStandard(costConfigModeDto.getWasteConfigName());
        costConfigDao.updateWaste(wasteCostConfigModeBo);

        CostConfigModeBo energyCostConfigModeBo = new CostConfigModeBo();
        energyCostConfigModeBo.setCommunityId(costConfigModeDto.getCommunityId());
        energyCostConfigModeBo.setChargeType(costConfigModeDto.getEnergyType());
        energyCostConfigModeBo.setStandard(costConfigModeDto.getEnergyConfigName());
        costConfigDao.updateEnergy(energyCostConfigModeBo);

        CostConfigModeBo elevatorCostConfigModeBo = new CostConfigModeBo();
        elevatorCostConfigModeBo.setCommunityId(costConfigModeDto.getCommunityId());
        elevatorCostConfigModeBo.setChargeType(costConfigModeDto.getElevatorType());
        elevatorCostConfigModeBo.setStandard(costConfigModeDto.getElevatorConfigName());
        costConfigDao.updateElevator(elevatorCostConfigModeBo);

        CostConfigModeBo waterCostConfigModeBo = new CostConfigModeBo();
        waterCostConfigModeBo.setCommunityId(costConfigModeDto.getCommunityId());
        waterCostConfigModeBo.setChargeType(costConfigModeDto.getWaterType());
        waterCostConfigModeBo.setStandard(costConfigModeDto.getWaterConfigName());
        costConfigDao.updateWater(waterCostConfigModeBo);

        CostConfigModeBo powerCostConfigModeBo = new CostConfigModeBo();
        powerCostConfigModeBo.setCommunityId(costConfigModeDto.getCommunityId());
        powerCostConfigModeBo.setChargeType(costConfigModeDto.getPowerType());
        powerCostConfigModeBo.setStandard(costConfigModeDto.getPowerConfigName());
        costConfigDao.updatePower(powerCostConfigModeBo);

        CostConfigModeBo heatCostConfigModeBo = new CostConfigModeBo();
        heatCostConfigModeBo.setCommunityId(costConfigModeDto.getCommunityId());
        heatCostConfigModeBo.setChargeType(costConfigModeDto.getHeatType());
        heatCostConfigModeBo.setStandard(costConfigModeDto.getHeatConfigName());
        costConfigDao.updateHeat(heatCostConfigModeBo);
        return buildSuccesResponse();
    }

    @Override
    public Response getCostConfigMode() {
        List<CostConfigPo> propertyCostConfigPoList = costConfigDao.getPropertyModeConfig();
        List<CostConfigPo> wasteCostConfigPoList = costConfigDao.getWasteModeConfig();
        List<CostConfigPo> energyOvertCostConfigPoList = costConfigDao.getEnergyOvertModeConfig();
        List<CostConfigPo> elevatorCostConfigPoList = costConfigDao.getElevatorModeConfig();
        List<CostConfigPo> waterCostConfigPoList = costConfigDao.getWaterModeConfig();
        List<CostConfigPo> powerCostConfigPoList = costConfigDao.getPowerModeConfig();
        List<CostConfigPo> heatCostConfigPoList = costConfigDao.getHeatModeConfig();
        CostConfigModeVo costConfigModeVo = new CostConfigModeVo();
        costConfigModeVo.setPropertyCostConfigList(propertyCostConfigPoList);
        costConfigModeVo.setWasteCostConfigList(wasteCostConfigPoList);
        costConfigModeVo.setEnergyOvertCostConfigList(energyOvertCostConfigPoList);
        costConfigModeVo.setElevatorCostConfigList(elevatorCostConfigPoList);
        costConfigModeVo.setWaterCostConfigList(waterCostConfigPoList);
        costConfigModeVo.setPowerCostConfigList(powerCostConfigPoList);
        costConfigModeVo.setHeatCostConfigList(heatCostConfigPoList);
        return buildSuccesResponse(costConfigModeVo);
    }

    private List<CostConfigBo> setData(CostConfigDto costConfigDto) {
        List<CostConfigBo> parkingSpaceUseDtoList = new ArrayList<>();
        for (int i = 0; i < userTypeArr.length; i++) {

            for (int j = 0; j < parkTypeArr.length; j++) {
                if (1 == userTypeArr[i]) {
                    if (1 == parkTypeArr[j]) {
                        CostConfigBo rentParkingSpaceUseDto = new CostConfigBo();
                        rentParkingSpaceUseDto.setIsFree(0);
                        rentParkingSpaceUseDto.setIsSale(0);
                        rentParkingSpaceUseDto.setParkType(1);
                        rentParkingSpaceUseDto.setMangerPrice(costConfigDto.getRentBottomManagerPrice());
                        rentParkingSpaceUseDto.setRentPrice(costConfigDto.getRentBottomRentPrice());
                        rentParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(rentParkingSpaceUseDto);
                    }
                    else if (2 == parkTypeArr[j]) {
                        CostConfigBo rentParkingSpaceUseDto = new CostConfigBo();
                        rentParkingSpaceUseDto.setIsFree(0);
                        rentParkingSpaceUseDto.setIsSale(0);
                        rentParkingSpaceUseDto.setParkType(2);
                        rentParkingSpaceUseDto.setMangerPrice(costConfigDto.getRentTopManagerPrice());
                        rentParkingSpaceUseDto.setRentPrice(costConfigDto.getRentTopRentPrice());
                        rentParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(rentParkingSpaceUseDto);
                    }
                    else if (3 == parkTypeArr[j]) {
                        CostConfigBo rentParkingSpaceUseDto = new CostConfigBo();
                        rentParkingSpaceUseDto.setIsFree(0);
                        rentParkingSpaceUseDto.setIsSale(0);
                        rentParkingSpaceUseDto.setParkType(3);
                        rentParkingSpaceUseDto.setMangerPrice(costConfigDto.getRentMidManagerPrice());
                        rentParkingSpaceUseDto.setRentPrice(costConfigDto.getRentMidRentPrice());
                        rentParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(rentParkingSpaceUseDto);
                    }
                }
                else if (2 == userTypeArr[i]) {
                    if (1 == parkTypeArr[j]) {
                        CostConfigBo saleParkingSpaceUseDto = new CostConfigBo();
                        saleParkingSpaceUseDto.setIsFree(0);
                        saleParkingSpaceUseDto.setIsSale(1);
                        saleParkingSpaceUseDto.setParkType(1);
                        saleParkingSpaceUseDto.setMangerPrice(costConfigDto.getSaleBottomManagerPrice());
                        saleParkingSpaceUseDto.setRentPrice(0d);
                        saleParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(saleParkingSpaceUseDto);
                    }
                    else if (2 == parkTypeArr[j]) {
                        CostConfigBo saleParkingSpaceUseDto = new CostConfigBo();
                        saleParkingSpaceUseDto.setIsFree(0);
                        saleParkingSpaceUseDto.setIsSale(1);
                        saleParkingSpaceUseDto.setParkType(2);
                        saleParkingSpaceUseDto.setMangerPrice(costConfigDto.getSaleTopManagerPrice());
                        saleParkingSpaceUseDto.setRentPrice(0d);
                        saleParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(saleParkingSpaceUseDto);
                    }
                    else if (3 == parkTypeArr[j]) {
                        CostConfigBo saleParkingSpaceUseDto = new CostConfigBo();
                        saleParkingSpaceUseDto.setIsFree(0);
                        saleParkingSpaceUseDto.setIsSale(1);
                        saleParkingSpaceUseDto.setParkType(3);
                        saleParkingSpaceUseDto.setMangerPrice(costConfigDto.getSaleMidManagerPrice());
                        saleParkingSpaceUseDto.setRentPrice(0d);
                        saleParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(saleParkingSpaceUseDto);
                    }
                }
                else if (3 == userTypeArr[i]) {
                    if (1 == parkTypeArr[j]) {
                        CostConfigBo freeParkingSpaceUseDto = new CostConfigBo();
                        freeParkingSpaceUseDto.setIsFree(1);
                        freeParkingSpaceUseDto.setIsSale(0);
                        freeParkingSpaceUseDto.setParkType(1);
                        freeParkingSpaceUseDto.setMangerPrice(costConfigDto.getFreeBottomManagerPrice());
                        freeParkingSpaceUseDto.setRentPrice(0d);
                        freeParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(freeParkingSpaceUseDto);
                    }
                    else if (2 == parkTypeArr[j]) {
                        CostConfigBo freeParkingSpaceUseDto = new CostConfigBo();
                        freeParkingSpaceUseDto.setIsFree(1);
                        freeParkingSpaceUseDto.setIsSale(0);
                        freeParkingSpaceUseDto.setParkType(2);
                        freeParkingSpaceUseDto.setMangerPrice(costConfigDto.getFreeTopManagerPrice());
                        freeParkingSpaceUseDto.setRentPrice(0d);
                        freeParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(freeParkingSpaceUseDto);
                    }
                    else if (3 == parkTypeArr[j]) {
                        CostConfigBo freeParkingSpaceUseDto = new CostConfigBo();
                        freeParkingSpaceUseDto.setIsFree(1);
                        freeParkingSpaceUseDto.setIsSale(0);
                        freeParkingSpaceUseDto.setParkType(3);
                        freeParkingSpaceUseDto.setMangerPrice(costConfigDto.getFreeMidManagerPrice());
                        freeParkingSpaceUseDto.setRentPrice(0d);
                        freeParkingSpaceUseDto.setCommunityId(costConfigDto.getCommunityId());
                        parkingSpaceUseDtoList.add(freeParkingSpaceUseDto);
                    }
                }
            }
        }
        return parkingSpaceUseDtoList;
    }

    private void setCostConfigType(CostConfigModeDto costConfigModeDto) {
        String mode = redisService.get("mode:" + costConfigModeDto.getCommunityId());
        JSONObject jsonObject = new JSONObject();
        JSONObject costPriceJson = new JSONObject();
        if (!StringUtils.isEmpty(mode)) {
            jsonObject = JSONObject.parseObject(mode);
        }
        costPriceJson.put("waterType", costConfigModeDto.getWaterType());
        costPriceJson.put("powerType", costConfigModeDto.getPowerType());
        costPriceJson.put("heaterType", costConfigModeDto.getHeatType());
        costPriceJson.put("propertyType", costConfigModeDto.getPropertyType());
        costPriceJson.put("wasteType", costConfigModeDto.getWasteType());
        costPriceJson.put("energyType", costConfigModeDto.getEnergyType());
        costPriceJson.put("elevator_type", costConfigModeDto.getElevatorConfigName());

        costPriceJson.put("property_type", costConfigModeDto.getPropertyConfigName());
        //costPriceJson.put("property_price", 1);
        costPriceJson.put("sanitary_waste_type", costConfigModeDto.getWasteConfigName());
        //costPriceJson.put("sanitary_waste_price", 1);
        costPriceJson.put("energy_type", costConfigModeDto.getEnergyConfigName());
        //costPriceJson.put("elevator_price", 1);

        costPriceJson.put("water_type", costConfigModeDto.getWaterConfigName());
        //costPriceJson.put("water_price", 1);
        costPriceJson.put("power_type", costConfigModeDto.getPowerConfigName());
        /*costPriceJson.put("power_price", 1);
        costPriceJson.put("heater_time", 4);
        costPriceJson.put("heater_municipal_price", 4);
        costPriceJson.put("heater_flow_price", 4);
        costPriceJson.put("heater_percent", 4);*/
        costPriceJson.put("heater_type", costConfigModeDto.getHeatConfigName());

        jsonObject.put("formObject", costPriceJson);
        redisService.set("mode:" + costConfigModeDto.getCommunityId(), jsonObject.toJSONString());
    }

    private void setCostConfigPrice(CostConfigDto costConfigDto) {
        String mode = redisService.get("mode:" + costConfigDto.getCommunityId());
        JSONObject jsonObject = new JSONObject();
        String floorIds = costConfigDto.getFloorIds();
        List<String> floorIdList = JSONObject.parseArray(floorIds, String.class);

        JSONArray floorDataArr = new JSONArray();
        if (!StringUtils.isEmpty(mode)) {
            jsonObject = JSONObject.parseObject(mode);
        }
        if (floorIdList != null&&floorIdList.size() > 0) {
            for (int i = 0; i < floorIdList.size(); i++) {
                JSONObject floorDataJson = new JSONObject();
                floorDataJson.put("property_price", costConfigDto.getPropertyPrice());
                floorDataJson.put("power_price", costConfigDto.getPowerPrice());
                floorDataJson.put("elevator_increase", costConfigDto.getElevatorIncrease());
                floorDataJson.put("heater_percent", costConfigDto.getHeatPercent());
                floorDataJson.put("floorId", floorIdList.get(i));
                String floorName = floorDao.getFloorById(floorIdList.get(i)).getFloorName();
                floorDataJson.put("floorNames", floorName);
                List<String> unitNameList = JSONObject.parseArray(costConfigDto.getUnitNames(), String.class);
                String unitNames = "";
                if (unitNameList != null&&unitNameList.size() > 0) {
                    for (String unitName: unitNameList) {
                        unitNames =  unitNames + unitName + ",";
                    }
                    unitNames = unitNames.substring(0, unitNames.length()-1);
                }
                floorDataJson.put("unitNames", unitNames);
                floorDataJson.put("energy_price", costConfigDto.getEnergyOvertPrice());
                floorDataJson.put("heater_time", costConfigDto.getHeatTime());
                floorDataJson.put("sanitary_waste_price", costConfigDto.getWastePrice());
                floorDataJson.put("heater_municipal_price", costConfigDto.getHeatPrice());
                floorDataJson.put("elevator_start_floor", costConfigDto.getElevatorStartLayer());
                floorDataJson.put("heater_flow_price", costConfigDto.getHeatFlowPrice());
                floorDataJson.put("elevator_price", costConfigDto.getElevatorPrice());
                floorDataJson.put("id", floorIdList.get(i));
                floorDataJson.put("communityId", costConfigDto.getCommunityId());
                floorDataJson.put("water_price", costConfigDto.getWaterPrice());
                floorDataArr.add(floorDataJson);
            }
        }
        jsonObject.put("floorData", floorDataArr);

        JSONObject carJson = new JSONObject();
        carJson.put("sold_top_car_type", "空中车位");
        carJson.put("sold_bottom_car_price", costConfigDto.getSaleBottomManagerPrice());
        carJson.put("carStandard", "元/月");
        carJson.put("special_bottoom_car_price", costConfigDto.getFreeBottomManagerPrice());
        carJson.put("lease_midle_car_type", "地上车位");
        carJson.put("lease_top_car_type", "空中车位");
        carJson.put("special_top_car_type", "空中车位");
        carJson.put("lease_bottom_car_price", costConfigDto.getRentBottomManagerPrice());
        carJson.put("special_midle_car_price", costConfigDto.getFreeTopManagerPrice());
        carJson.put("sold_bottom_car_type", "地下车位");
        carJson.put("lease_bottom_car_type", "地下车位");
        carJson.put("lease_midle_car_price", costConfigDto.getRentTopRentPrice());
        carJson.put("sold_midle_car_price", costConfigDto.getSaleTopManagerPrice());
        carJson.put("special_midle_car_type", "地上车位");
        carJson.put("lease_bottom_car_manage", costConfigDto.getRentBottomManagerPrice());
        carJson.put("sold_top_car_price", costConfigDto.getSaleMidManagerPrice());
        carJson.put("lease_midle_car_manage", costConfigDto.getRentTopManagerPrice());
        carJson.put("lease_top_car_manage", costConfigDto.getRentMidManagerPrice());
        carJson.put("special_top_car_price", costConfigDto.getFreeMidManagerPrice());
        carJson.put("special_bottoom_car_type", "地下车位");
        carJson.put("lease_top_car_price", costConfigDto.getRentMidRentPrice());
        carJson.put("sold_midle_car_type", "地上车位");
        jsonObject.put("carData", carJson);

        redisService.set("mode:" + costConfigDto.getCommunityId(), jsonObject.toJSONString());
    }


}
