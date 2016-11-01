package edu.aurora.oilchange;

import java.math.BigDecimal;

/**
 * A unit of engine oil, with type, brand, quantity, price, and oil filter brand
 * and cost.
 */
public class Oil implements Comparable<Oil> {
    /**
     * The type of engine oil
     */
    private String oilType;

    /**
     * The brand of engine oil
     */
    private String oilBrand;

    /**
     * The quantity of engine oil in quarts
     */
    private int quantity;

    // this is a BigDecimal to prevent rounding errors
    /**
     * The price per quart of oil
     */
    private BigDecimal pricePerQuart;

    /**
     * The brand of oil filter
     */
    private String filterBrand;

    // see above
    /**
     * The cost of the oil filter
     */
    private BigDecimal filterCost;

    /**
     * Creates a new Oil object with default parameters
     */
    public Oil() {
        this("", "", 0, BigDecimal.ZERO, "", BigDecimal.ZERO);
    }

    /**
     * Creates a new Oil object with the given parameters, accepting doubles to
     * represent currency.
     *
     * @param oilType       type of oil
     * @param oilBrand      brand of oil
     * @param quantity      quantity of oil in quarts
     * @param pricePerQuart price of oil in quarts
     * @param filterBrand   brand of oil filter
     * @param filterCost    price of oil filter
     */
    public Oil(String oilType, String oilBrand, int quantity, double pricePerQuart, String filterBrand,
               double filterCost) {
        this(oilType, oilBrand, quantity, BigDecimal.valueOf(pricePerQuart), filterBrand,
                BigDecimal.valueOf(filterCost));
    }

    /**
     * Creates a new Oil object with the given parameters
     *
     * @param oilType       type of oil
     * @param oilBrand      brand of oil
     * @param quantity      quantity of oil in quarts
     * @param pricePerQuart price of oil in quarts
     * @param filterBrand   brand of oil filter
     * @param filterCost    price of oil filter
     */
    public Oil(String oilType, String oilBrand, int quantity, BigDecimal pricePerQuart, String filterBrand,
               BigDecimal filterCost) {
        this.oilType = oilType;
        this.oilBrand = oilBrand;
        this.quantity = quantity;
        this.pricePerQuart = pricePerQuart;
        this.filterBrand = filterBrand;
        this.filterCost = filterCost;
    }

    /**
     * Returns the type of oil
     *
     * @return the type of oil
     */
    public String getOilType() {
        return oilType;
    }

    /**
     * Sets the oil type to the given value
     *
     * @param oilType the new oil type
     */
    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    /**
     * Returns the brand of oil
     *
     * @return the brand of oil
     */
    public String getOilBrand() {
        return oilBrand;
    }

    /**
     * Sets the oil brand to the given value
     *
     * @param oilBrand the new oil brand
     */
    public void setOilBrand(String oilBrand) {
        this.oilBrand = oilBrand;
    }

    /**
     * Returns the quantity of oil in quarts
     *
     * @return the quantity of oil
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of oil to the given value in quarts
     *
     * @param quantity the new oil quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the price per quart of oil
     *
     * @return the price per quart of oil
     */
    public BigDecimal getPricePerQuart() {
        return pricePerQuart;
    }

    /**
     * Sets the price per quart of oil to the given value
     *
     * @param pricePerQuart the new price per quart
     */
    public void setPricePerQuart(BigDecimal pricePerQuart) {
        this.pricePerQuart = pricePerQuart;
    }

    /**
     * Returns the brand of oil filter for the oil
     *
     * @return the brand of oil filter
     */
    public String getFilterBrand() {
        return filterBrand;
    }

    /**
     * Sets the brand of oil filter to the given value
     *
     * @param filterBrand the new oil filter brand
     */
    public void setFilterBrand(String filterBrand) {
        this.filterBrand = filterBrand;
    }

    /**
     * Returns the price of the oil filter
     *
     * @return the price of the oil filter
     */
    public BigDecimal getFilterCost() {
        return filterCost;
    }

    /**
     * Sets the price of the oil filter
     *
     * @param filterCost the new price
     */
    public void setFilterCost(BigDecimal filterCost) {
        this.filterCost = filterCost;
    }

    /**
     * Returns a string representation of the oil
     *
     * @return a string representing the oil
     */
    @Override
    public String toString() {
        return "Oil{" + "oilType='" + oilType + '\'' +
                ", oilBrand='" + oilBrand + '\'' +
                ", quantity=" + quantity +
                ", pricePerQuart=$" + pricePerQuart +
                ", filterBrand='" + filterBrand + '\'' +
                ", filterCost=$" + filterCost +
                '}';
    }

    @Override
    public int compareTo(Oil o) {
        if (this.getOilBrand().compareTo(o.getOilBrand()) > 1)
            return 1;
        else if (this.getOilBrand().compareTo(o.getOilBrand()) < 1)
            return -1;
        else if (this.getOilType().compareTo(o.getOilType()) > 1)
            return 1;
        else if (this.getOilType().compareTo(o.getOilType()) < 1)
            return -1;
        else if (this.getPricePerQuart().compareTo(o.getPricePerQuart()) < 1)
            return 1;
        else if (this.getPricePerQuart().compareTo(o.getPricePerQuart()) > 1)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Oil oil = (Oil)o;

        if (quantity != oil.quantity) return false;
        if (oilType != null ? !oilType.equals(oil.oilType) : oil.oilType != null) return false;
        if (oilBrand != null ? !oilBrand.equals(oil.oilBrand) : oil.oilBrand != null) return false;
        if (pricePerQuart != null ? !pricePerQuart.equals(oil.pricePerQuart) : oil.pricePerQuart != null) return false;
        if (filterBrand != null ? !filterBrand.equals(oil.filterBrand) : oil.filterBrand != null) return false;
        return filterCost != null ? filterCost.equals(oil.filterCost) : oil.filterCost == null;

    }

    @Override
    public int hashCode() {
        int result = oilType != null ? oilType.hashCode() : 0;
        result = 31 * result + (oilBrand != null ? oilBrand.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (pricePerQuart != null ? pricePerQuart.hashCode() : 0);
        result = 31 * result + (filterBrand != null ? filterBrand.hashCode() : 0);
        result = 31 * result + (filterCost != null ? filterCost.hashCode() : 0);
        return result;
    }
}
