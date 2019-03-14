/**
     * 对List中的map按照某个字段升序排列
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> ascList(List<Map<String, Object>> list, String field) {
        SystemLogHelper.info("method:ascList 正在排序" + " ...", true);
        //对list进行重新按照count进行升序-从小到大
        if (null != list && list.size() > 0) {
            Collections.sort(list, new Comparator<Map<String, Object>>() {
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    if ("hour".equals(field)) {
                        String hour1 = o1.get(field).toString();
                        String hour2 = o2.get(field).toString();
                        DateFormat formart = new SimpleDateFormat("HH:mm");
                        Date date1 = null;//从你list里面拿出来的一个
                        Date date2 = null;//从你list里面拿出来的第二个
                        try {
                            date1 = formart.parse(hour1);
                            date2 = formart.parse(hour2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return date1.compareTo(date2);
                    } else {
                        String field1 = o1.get(field).toString();
                        String field2 = o2.get(field).toString();
                        return field1.compareTo(field2);
                    }
                }
            });
        }
        return list;
    }
