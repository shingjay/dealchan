class Deal < ActiveRecord::Base

	DEALS_PER_PAGE = 5

	def self.get_deal_with_price_range_and_city(min_price, max_price, city, page_number)
		where("currentPrice >= :min_price AND currentPrice < :max_price", {
			:min_price => min_price,
			:max_price => max_price
		})
		.where( :city => city)
		.order("currentPrice ASC")
		.limit(DEALS_PER_PAGE)
		.offset(DEALS_PER_PAGE * (page_number - 1))
	end


	def self.get_deal_with_category_and_city(category, city, page_number)
		where(:category => category, 
			:city => city)
		.order("discount DESC")
		.limit(DEALS_PER_PAGE)
		.offset(DEALS_PER_PAGE * (page_number-1))
	end


end
